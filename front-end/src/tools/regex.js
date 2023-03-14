// eslint-disable-next-line
const regExp = /[^a-zA-Z0-9]/

// 한글 포함
// eslint-disable-next-line
const regExp2 = /[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]/

// eslint-disable-next-line
const regExpEng = /[a-zA-Z]/

// eslint-disable-next-line
const regExpNum = /[0-9]/


// 특수문자, 띄어쓰기, 한글 입력 방지
function characterCheck(str) {
  let adjustedStr = str
  let content = null
  
  let idx = adjustedStr.length - 1
  while (idx > -1) {
    // 특수문자가 있을 경우
    if (regExp.test(adjustedStr[idx])) {
      adjustedStr = adjustedStr.substring(0, idx)
      content = '영어 대소문자와 숫자만 입력해주세요.'
    }
    idx -= 1
  }
  if (!adjustedStr.length) {
    return [null, content]
  } else {
      return [adjustedStr, content]
    }
}

// 위에서 한글은 포함
function characterCheckNickname(str) {
  let adjustedStr = str
  let content = null

  let idx = adjustedStr.length - 1
  while (idx > -1) {
    // 특수문자가 있을 경우
    if (regExp2.test(adjustedStr[idx])) {
      adjustedStr = adjustedStr.substring(0, idx)
      content = '영어 대소문자, 한글, 숫자만 입력해주세요.'
    }
    idx -= 1
  }

  if (!adjustedStr.length) {
    return [null, content]
  } else {
    return [adjustedStr, content]
  }
}

// 영어 포함 여부
function englishCheck(str) {
  if ( regExpEng.test(str) ) {
    return true
  } else {
    return false
  }
}

// 숫자 포함 여부
function numberCheck(str) {
  // eslint-disable-next-line
  if ( regExpNum.test(str) ) {
    return true
  } else {
    return false
  }
}

export { regExp, regExpEng, regExpNum, characterCheck, characterCheckNickname, englishCheck, numberCheck }
