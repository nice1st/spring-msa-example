# spring-msa-example

* spring msa 사내 적용 전 테스트

<img src="/resources/a_1.png" width="549px" height="407px"/> 
<img src="/resources/a_2.png" width="983px" height="662px"/> 
<img src="/resources/a_3.png" width="1048px" height="620px"/> 

# 빌드 & 배포

* window 환경에서 docker toolbox 이용해 docker 설치 함
* mysql, jenkins, rabbitmq 컨테이너 실행 (설정포함)
* root 에 Jenkinsfile pipeline 작성
* subproject 에 Dockerfile build script 작성
* jenkins 수동 run (또는 trigger)

# 참고

* https://github.com/taes-k/spring-example/tree/master/spring-msa
* https://gwonsungjun.github.io/articles/2019-04/gradle_multi_module
* https://woowabros.github.io/study/2019/07/01/multi-module.html