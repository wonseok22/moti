# SSAFY 2학기 공통PJT(2023.01.03 ~ 2023.02.17)

# 동기부여가 필요한 당신을 위한 SNS- moti

## 서비스 페이지: [https://moti.today/](https://moti.today/)

## 서비스 소개 영상: {ucc}

## 서비스 개요

  우리는 늘 계획을 세웁니다. 나를 위해, 내가 사랑하는 사람들을 위해 우리는 계획을 세우고 그것을 해내기 위해 스스로 동기부여하지만 실천하는 것이 쉽지 않을 때가 많습니다. 피곤하다는 이유로 운동을 가지 않기도 하고, 바쁜 삶에 치여 부모님께 연락 한 통 드리지 못하곤 합니다. 우리는 목표의식은 있지만 실천하기 어려워하는 사람들을 위해 moti를 기획하게 되었습니다. 

## 주요 기능

- 성장 플랜 선택
    - 하단 네비게이션바의 미션수행 탭에서 수행할 성장 플랜을 선택합니다.
    - 세 개의 큰 카테고리(건강, 관계, 행복) 안에 각각 2개의 성장 플랜이 들어있습니다. 사용자들은 각 성장 플랜을 구성하는 미션들을 확인한 뒤 자신이 원하는 성장 플랜을 최종적으로 선택합니다.
    - 모든 성장 플랜은 해당 성장 플랜의 취지를 가장 잘 나타내는 꽃말을 가진 꽃으로 대표됩니다.
    
  <img src="/uploads/1cfd432d2bc42cc684be1c7ae7b7854b/intro_1.gif"  width="280" height="600">

    

- 미션 인증 피드 작성
    - 수행한 미션에 대한
  <img src="/uploads/3d91987483667a53f8dcc1773455f3d8/intro_2.gif"  width="280" height="600">
  

## Architecture
  <img src="/uploads/36108896807ab9090cc3a09069619f68/아키텍쳐_최종본.jpg"  width="800" height="550">



## 기술 스택

  - 협업, 버전관리: ![img](https://camo.githubusercontent.com/a9a95986631c3d4945a63d42d2864e3918a834d672d907e174a29f743a1bc3f1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6769742d4630353033323f7374796c653d666f722d7468652d6261646765266c6f676f3d676974266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/d20c06f1854face8c434a4fa2ffa62a2c6d52368120cc7dafd77166da5732caf/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f74696f6e2d3030303030303f7374796c653d666f722d7468652d6261646765266c6f676f3d6e6f74696f6e266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/Jira -0052CC?style=for-the-badge&logo=Jira Software&logoColor=white"><img src="https://img.shields.io/badge/Gitlab -FC6D26?style=for-the-badge&logo=Gitlab&logoColor=white">
  - 언어: ![img](https://camo.githubusercontent.com/146641825a4dcaf7d047629441f6596b8d9d7327ec8c8104ea54d3b6aa1080b3/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176615363726970742d4637444631453f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661736372697074266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/5a7100155d1a7b75357a90e8810530b21c8723c59f2976d0dafc7950205336d7/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f68746d6c352d4533344632363f7374796c653d666f722d7468652d6261646765266c6f676f3d68746d6c35266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/395bcd1fa353e86f422e5f01abf3260b8c76720be050e5f4688ab7fc7634f50f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4353532d3135373242363f7374796c653d666f722d7468652d6261646765266c6f676f3d63737333266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  - FE/BE framework:![img](https://camo.githubusercontent.com/d4bf3d84fc3a4e4cc4f10f59cdbae19b2d7cf13bd178f51adea9b9add58c94be/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f5675652d3446633038443f7374796c653d666f722d7468652d6261646765266c6f676f3d7675652e6a73266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">> 
  - DB:  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"> 
  - 배포: ![img](https://camo.githubusercontent.com/c75eb74744dd435c8f353a621e97b392201c530225b32b1be88d6cd38a1b1448/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f416d617a6f6e4157532d4646393930423f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e617773266c6f676f436f6c6f723d7768697465) <img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white"> <img src="https://img.shields.io/badge/Docker -2496ED?style=for-the-badge&logo=docker&logoColor=white">
  - 디자인: ![img](https://camo.githubusercontent.com/47e6264f9d0f6ddd400336f6755634301f99d25998f5663caa7f4f6353ce8146/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537761676765722d3835454132443f7374796c653d666f722d7468652d6261646765266c6f676f3d73776167676572266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/078a1d511beddd849ea23d8cdbb04fc820c1e5dd10114f9b70511ab1a3fb8005/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f426f6f7473747261702d373935313242333f7374796c653d666f722d7468652d6261646765266c6f676f3d626f6f747374726170266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/8ef77581a062572d7a25d237ccc46271ce724b3372da00a06291db77e8157e0d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f466f6e74417765736f6d652d3532384444373f7374796c653d666f722d7468652d6261646765266c6f676f3d466f6e74417765736f6d65266c6f676f436f6c6f723d7768697465)


## 

## 프로젝트 산출물

## 서비스 상세

## 팀원 소개
