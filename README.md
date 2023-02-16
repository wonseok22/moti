# <img src="./docs/images/logo.png" height="32px">moti - 동기부여가 필요한 당신을 위한 SNS

<br>

## **서비스 페이지: [https://moti.today/](https://moti.today/)**
<br>

## **서비스 소개 영상: [moti UCC](https://drive.google.com/file/d/1yH3LDSlq1AfVlKQXpxzwcc-TjYsNRSsW/view?usp=share_link)**

<br>

## **프로젝트 기간**
SSAFY 2학기 공통PJT(2023.01.03 ~ 2023.02.17)

<br>

## **서비스 개요**

우리는 늘 계획을 세웁니다. 나를 위해, 내가 사랑하는 사람들을 위해 우리는 계획을 세우고 그것을 해내기 위해 스스로 동기부여하지만 실천하는 것이 쉽지 않을 때가 많습니다. 피곤하다는 이유로 운동을 가지 않기도 하고, 바쁜 삶에 치여 부모님께 연락 한 통 드리지 못하곤 합니다. 우리는 목표의식은 있지만 실천하기 어려워하는 사람들을 위해 moti를 기획하게 되었습니다.

**moti는 우리의 내재적 동기를 증진하고, 미션 공동체감을 형성해주어 우리가 ‘실천이 습관이 되는 삶’을 살아갈 수 있도록 도울 것입니다.**

<br>

## **주요 기능**

### **성장 플랜 선택**
  - 하단 네비게이션바의 미션수행 탭에서 수행할 성장 플랜을 선택합니다.
  - 세 개의 큰 카테고리(건강, 관계, 행복) 안에 각각 2개의 성장 플랜이 들어있습니다. 사용자들은 각 성장 플랜을 구성하는 미션들을 확인한 뒤 자신이 원하는 성장 플랜을 최종적으로 선택합니다.
  - 모든 성장 플랜은 해당 성장 플랜의 취지를 가장 잘 나타내는 꽃말을 가진 꽃으로 대표됩니다.
  
    <img src="./docs/images/plan_select.gif" height="400">

### **미션 후기 피드 작성**
  - 미션을 수행하고 미션 후기 피드를 작성합니다.
  
    <img src="./docs/images/feed_create.gif" height="400">

### **피드 좋아요, 댓글 쓰기**
  - 사용자들이 올린 피드에 좋아요를 누를 수 있고, 댓글을 작성할 수 있습니다.
  
    <img src="./docs/images/like_and_comment.gif" height="400">
        
### **성장 플랜 피드 모아보기**
  - 수행한 성장 플랜의 피드를 프로필에서 한 눈에 확인할 수 있습니다.
  
    <img src="./docs/images/feed_all.gif" height="400">
        
### **업적(뱃지)**
  - 피드 작성 수, 등록한 댓글 수, 성장 플랜 완수 수 등 활동에 따라 뱃지가 주어집니다.
  - 획득한 뱃지 중 대표 뱃지를 설정하여 닉네임 옆에 띄울 수 있습니다.
  
    <img src="./docs/images/badge.gif" height="400">
        
### **검색**
  - 게시글, 성장 플랜, 계정 검색을 할 수 있습니다.
  
    <img src="./docs/images/search.gif" height="400">

### **성장 플랜 건의**
  - 새로운 성장 플랜 아이디가 있다면 프로필의 ‘성장플랜 건의’함에서 성장 플랜과 그에 해당하는 미션을 제안할 수 있습니다.
  
    <img src="./docs/images/suggestion.gif" height="400">
        

### **기타 기능**
  - 회원가입/로그인: 일반(이메일 인증), 카카오톡, 구글
  - 프로필 수정: 닉네임, 프로필 사진, 자기 소개
  - 회원정보 수정: 비밀번호 변경, 회원 탈퇴
  - 팔로우: 프로필에서 팔로우, 팔로워 리스트 확인 가능

<br>

## **Architecture**
  <img src="./docs/images/architecture.jpg"  width="800" height="550">

<br>

## **기술 스택**
  - 협업, 버전관리: ![img](https://camo.githubusercontent.com/a9a95986631c3d4945a63d42d2864e3918a834d672d907e174a29f743a1bc3f1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6769742d4630353033323f7374796c653d666f722d7468652d6261646765266c6f676f3d676974266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/d20c06f1854face8c434a4fa2ffa62a2c6d52368120cc7dafd77166da5732caf/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f74696f6e2d3030303030303f7374796c653d666f722d7468652d6261646765266c6f676f3d6e6f74696f6e266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/Jira -0052CC?style=for-the-badge&logo=Jira Software&logoColor=white"><img src="https://img.shields.io/badge/Gitlab -FC6D26?style=for-the-badge&logo=Gitlab&logoColor=white">
  - 언어: ![img](https://camo.githubusercontent.com/146641825a4dcaf7d047629441f6596b8d9d7327ec8c8104ea54d3b6aa1080b3/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176615363726970742d4637444631453f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661736372697074266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/5a7100155d1a7b75357a90e8810530b21c8723c59f2976d0dafc7950205336d7/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f68746d6c352d4533344632363f7374796c653d666f722d7468652d6261646765266c6f676f3d68746d6c35266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/395bcd1fa353e86f422e5f01abf3260b8c76720be050e5f4688ab7fc7634f50f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4353532d3135373242363f7374796c653d666f722d7468652d6261646765266c6f676f3d63737333266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  - FE/BE framework: ![img](https://camo.githubusercontent.com/d4bf3d84fc3a4e4cc4f10f59cdbae19b2d7cf13bd178f51adea9b9add58c94be/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f5675652d3446633038443f7374796c653d666f722d7468652d6261646765266c6f676f3d7675652e6a73266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"> 
  - DB: <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"><img src="https://img.shields.io/badge/Redis-F74747?style=for-the-badge&logo=Redis&logoColor=white"><img src="https://img.shields.io/badge/Amazon RDS-e5d200?style=for-the-badge&logo=Amazon RDS&logoColor=black">
  - 배포: ![img](https://camo.githubusercontent.com/c75eb74744dd435c8f353a621e97b392201c530225b32b1be88d6cd38a1b1448/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f416d617a6f6e4157532d4646393930423f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e617773266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white"><img src="https://img.shields.io/badge/Docker -2496ED?style=for-the-badge&logo=docker&logoColor=white">
  - CI/CD:  <img src="https://img.shields.io/badge/jenkins-7b2d00?style=for-the-badge&logo=jenkins&logoColor=white">
  - 디자인: ![img](https://camo.githubusercontent.com/47e6264f9d0f6ddd400336f6755634301f99d25998f5663caa7f4f6353ce8146/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537761676765722d3835454132443f7374796c653d666f722d7468652d6261646765266c6f676f3d73776167676572266c6f676f436f6c6f723d7768697465)![img](https://camo.githubusercontent.com/8ef77581a062572d7a25d237ccc46271ce724b3372da00a06291db77e8157e0d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f466f6e74417765736f6d652d3532384444373f7374796c653d666f722d7468652d6261646765266c6f676f3d466f6e74417765736f6d65266c6f676f436f6c6f723d7768697465)<img src="https://img.shields.io/badge/sass-c48da4?style=for-the-badge&logo=sass&logoColor=white">

<br>

## 프로젝트 산출물
  - [요구사항 명세서](https://docs.google.com/spreadsheets/d/1VCHoI7GupFWbiQet0yiWQ8CtMJXy55EqK7BFbsyXBNE/edit?usp=sharing)
  - [기능 정의서](https://docs.google.com/spreadsheets/d/1RU2K2406LPfLwlQ1BZtzjaTzlWlzKXkbtrGJGKwhSkg/edit?usp=sharing)
  - [와이어 프레임](https://www.figma.com/file/YNlhSos5Lc5T7x8kPRZ9Xa/wireframe?node-id=1%3A4&t=eUwB9h039oUJrfVB-1)
  - [ERD](https://drive.google.com/file/d/162xcVEjiTRk7FI0koiqdyPW6W22sieOB/view?usp=sharing)
  - [API 명세서](https://docs.google.com/spreadsheets/d/1duRRLAfNjpaQ-DNTC_Gr5_93S6W2WguajCkwFq1mzKk/edit?usp=sharing)
  - [UCC](https://drive.google.com/file/d/1yH3LDSlq1AfVlKQXpxzwcc-TjYsNRSsW/view?usp=share_link)
  - 최종 발표 자료
  - [포팅 매뉴얼](https://lab.ssafy.com/s08-webmobile2-sub2/S08P12A509/-/blob/master/exec/%ED%8F%AC%ED%8C%85%EB%A7%A4%EB%89%B4%EC%96%BC_A509_moti.pdf)
  - [서비스 기획서](https://docs.google.com/document/d/19p0VtpNlteqFQRUuHLccIS_PhBel7xTYJ-Y9FSJ4dSQ/edit?usp=sharing)
  - [유사 서비스 조사](https://docs.google.com/spreadsheets/d/1teaXvvrvGnj2uXY0SJvy8_VOWVV7pqUdUF4t35g7PZA/edit?usp=sharing)
  - [서비스 전략 구상 및 학습](  https://docs.google.com/document/d/1he7Bx1rYmBEvgwee7ZHUk5tWowbOb8AGEAZ-nKhbLx8/edit?usp=sharing)
  

<br>

## 팀원 소개

