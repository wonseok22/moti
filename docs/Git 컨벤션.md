# Git 컨벤션
>수정일: 23.01.26.

---

## 브랜치 전략

- feature 브랜치
    - 실질적인 기능 구현
    - 각 기능은 상위에 있는 dev-front, dev-back으로 MR을 보내 merge 한다.
    - 머지 완료 후 해당 브랜치 삭제
- develop
    - 배포할 수준의 기능을 갖추면 release 브랜치로 머지
- release
    - 충분한 테스트를 통해 bug fix
    - master 브랜치로 머지
- 커밋
    - 기능 단위로 커밋
        - 예시: feature-back/profile/create
    - 로컬에서 충분한 테스트 후 커밋 및 MR 보내기
    
## Commit 전략
    
- 커밋 메시지
    - 기능 설명
        - ex. 로그인 구현, 로그인 버그 수정
- MR description
    - 커밋 메시지와 동일