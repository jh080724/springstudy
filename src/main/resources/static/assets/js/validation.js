// 회원가입 입력 검증 처리
let idFlag = false;

// 계정 중복 검사를 백앤드로 비동기 요청(fetch) 보내기는 함수
async function fetchDuplicateCheck(idValue) {
  // fetch(`/members/check?type=account&keyword=${idValue}`)
  //   .then((res) => res.json()) // json 꺼내고
  //   .then((flag) => (idFlag = flag)); // idFlag에 응답결과 집어넣기
  const res = await fetch(`/members/check?type=account&keyword=${idValue}`);
  const flag = await res.json();
}

// 계정입력 검증, 태그 아이디 가져오기
const $idInput = document.getElementById("user_id");

$idInput.addEventListener("keyup", async (e) => {
  // 키보드 이벤트
  // 입력값 읽어오기
  const idValue = $idInput.value;

  // 아이디 검사 정규 표현식
  // Regular Expression(Regex): 프로그래밍 언어에서 문자열을 다룰 때
  // 일정한 패턴을 표현하는 형식언어
  const accountPattern = /^[a-zA-Z0-9]{4,14}$/;

  // 검증 메시지를 입력할 span
  const $idChk = document.getElementById("idChk");

  if (idValue.trim() === "") {
    $idInput.style.borderColor = "red";
    $idChk.innerHTML = '<b class="warning">[아이디는 필수값입니다.]</b>';
  } else if (!accountPattern.test(idValue)) {
    $idInput.style.borderColor = "red";
    $idChk.innerHTML =
      '<b class="warning">[아이디는 4~14글자 사이 영문,숫자로 입력해주세요.]</b>';
  } else {
    // 아이디 중복 검사(백앤드 연동- fetch())
    // async, await를 통해 promise 객체 처리를 좀 더 쉽게 진행할 수 있고,
    // 비동기 방식의 코드 순서를 보장할 수 있다.
    await fetchDuplicateCheck(idValue);

    // 아이디가 존재할 경우
    if (idFlag) {
      $idInput.style.borderColor = "red";
      $idChk.innerHTML =
        '<b class="warning">[아이디가 중복되었습니다. 다른 아이디를 사용하세요!]</b>';
    } else {
      $idInput.style.borderColor = "skyblue";
      $idChk.innerHTML = '<b class="succcess">[사용 가능한 아이디 입니다.]</b>';
    }
  }
});

// 패스워드 검사 정규표현식
const passwordPattern =
  /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
// 이름 검사 정규표현식
const namePattern = /^[가-힣]+$/;
// 이메일 검사 정규표현식
const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
