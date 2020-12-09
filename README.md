# naver예약서비스 

사용기술 - css, javascript, jQuery, Ajax, java, jsp, jstl, el, spring, springJDBC

## 인텔리제이에서 이클립스 프로젝트 띄우기

* git clone https://github.com/wook101/ReservationService.git
* Import Project > Eclips project
* Project Struct > Project SDK > jdk1.8
* Project Struct > Dependencies > Modules SDK > jdk1.8
* Add framwork support > Maven add

* Tomcat Server설정 > Local > Deployment > artifect에서 프로젝트(ReservationService:war exploded) add
* Application context : "/" 루트경로로 변경
* Tomcat Server Run !! 8080포트 접속 !!