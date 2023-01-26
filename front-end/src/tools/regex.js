// eslint-disable-next-line
const regExp = /[^a-zA-Z0-9]/
// eslint-disable-next-line
const regExpEng = /[a-zA-Z]/
// eslint-disable-next-line
const regExpNum = /[0-9]/

// 특수문자 입력 방지
function characterCheck(str) {
  // 지금은 띄어쓰기도 특수문자 처리
  if ( regExp.test(str) ) {
    str = str.substring( 0, str.length - 1 ) // 입력한 특수문자 한자리 지움
  } 
  return str
}

// 영어 포함 여부
function englishCheck(str) {
  // eslint-disable-next-line
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

export { regExp, regExpEng, regExpNum, characterCheck, englishCheck, numberCheck }
