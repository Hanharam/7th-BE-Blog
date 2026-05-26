# 1. 베이스 이미지 설정
FROM amazoncorretto:21-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 이미지 메타데이터 라벨링
LABEL maintainer="LEETS BLOG"
LABEL description="LEETS 7TH BLOG"

# 4. 리눅스 패키지 설치 및 유저/권한 설정
# - apk add: Alpine 리눅스의 패키지 매니저로 curl을 설치
# - addgroup & adduser: root 권한이 아닌, 보안이 강화된 일반 유저 'spring'을 컨테이너 안에 만들기
# - mkdir & chown: 로그를 저장할 /app/logs 폴더를 만들고, 이 폴더를 포함한 /app 전체 소유권을 'spring' 유저에게 넘감
RUN apk add --no-cache curl && \
    addgroup -S spring && \
    adduser -S spring -G spring && \
    mkdir -p /app/logs && \
    chown -R spring:spring /app

# 5. JAR 복사 및 권한 제한
# - build/libs/ 폴더 안에 있는 .jar 파일을 컨테이너 내부의 /app/app.jar로 복사
# - 복사할 때 소유자를 'spring' 유저로 지정(--chown)하고, 파일 권한을 읽기 전용(444)으로 제한하여 보안을 높임
COPY --chown=spring:spring build/libs/*.jar app.jar
RUN chmod 444 app.jar

# 6. 실행 유저 변경
# 기본값은 root 권한에서 spring 일반 유저 권한으로 전환
USER spring:spring

# 7. 포트 개방 명시
EXPOSE 8080

# 8. 컨테이너 환경에 맞는 JVM 옵션 세팅
# - UseContainerSupport: 자바가 컨테이너(도커)의 메모리 제한을 정확하게 인식하게 함
# - MaxRAMPercentage=75.0: 도커 컨테이너에게 할당된 전체 메모리 중 최대 75%까지 자바(Heap)가 쓰도록 제한
# - UseG1GC: 자바 9 이상부터 기본이자 대용량 메모리에 최적화된 G1 가비지 컬렉터를 명시
# - ExitOnOutOfMemoryError: 메모리 부족(OOM) 에러가 나면 프로세스를 즉시 종료시켜서, 도커나 쿠버네티스가 컨테이너를 자동으로 재시작할 수 있게 만들기
# - HeapDumpOnOutOfMemoryError & HeapDumpPath: OOM 발생 시 원인 분석을 위해 메모리 덤프 파일을 지정된 안전한 경로(/app/logs)에 남김
# - Duser.timezone=Asia/Seoul: 서버 시간을 한국 시간(KST)으로 맞춤
# - Djava.security.egd: 자바가 무작위 값을 만들 때 성능 저하가 없는 리눅스 장치(/dev/urandom)를 쓰도록 유도
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:+UseG1GC \
               -XX:+ExitOnOutOfMemoryError \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/app/logs/heapdump.hprof \
               -Duser.timezone=Asia/Seoul \
               -Djava.security.egd=file:/dev/./urandom"

# 9. 애플리케이션 실행 명령
# - sh -c: 앞서 설정한 $JAVA_OPTS 환경변수를 리눅스 쉘이 정상적으로 읽어서 확장할 수 있도록 도와줌
# - exec: 자바 프로세스를 컨테이너의 메인 프로세스(PID 1)로 격상
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]

# 10. 헬스 체크
# 스프링 프로젝트에 'Spring Boot Actuator' 의존성, /actuator/health 엔드포인트 필요
# 도커가 30초마다 서버 상태를 체크
# HEALTHCHECK --interval=30s --timeout=5s CMD curl -f http://localhost:8080/actuator/health || exit 1
