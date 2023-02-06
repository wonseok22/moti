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
  if ( regExp.test(str) ) {
    alert('영어 대소문자와 숫자만 입력해주세요.')
    adjustedStr = str.substring( 0, str.length - 1 ) // 입력한 특수문자 한자리 지움
  }
  if (!adjustedStr.length) {
    return null
  } else {
    return adjustedStr
  }
}

// 위에서 한글은 포함
function characterCheckNickname(str) {
  let adjustedStr = str
  if ( regExp2.test(str) ) {
    alert('영어 대소문자, 한글, 숫자만 입력해주세요.')
    adjustedStr = str.substring( 0, str.length - 1 ) // 입력한 특수문자 한자리 지움
  } 
  if (!adjustedStr.length) {
    return null
  } else {
    return adjustedStr
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
