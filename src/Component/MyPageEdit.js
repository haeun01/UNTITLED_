import styled from "styled-components";
import { Title } from "./Styles";
import { SessionCurrent } from "./SessionCurrent";
import axios from "axios";

const FlexC = styled.div`
  width: 50%;
  margin: auto;
  display: flex;
  flex-direction: column;
  font-weight: 100;
  font-size: 300px;

  /* const Title {
    font-weight: 500;
    font-size: 300px;
  } */
`;
const Flex = styled.div`
  display: flex;
  justify-content: end;
  margin-top: 10px;
`;
const Input = styled.input`
  width: 100%;
  padding: 10px;
  padding-left: 30px;
  margin: 10px 0;
  border: 1px solid white;
  border-radius: 30px;
  background-color: black;
  color: white;
  outline: none;
  font-size: 16px;
`;
const EditBtn = styled.div`
  padding: 10px 70px;
  border-radius: 50px;
  background-color: white;
  color: black;
  font-size: 16px;
  font-weight: 300;
  cursor: pointer;
  transition: background-color 0.3s; /* 부드러운 전환 효과 추가 */

  &:hover {
    background-color: #561689;
    color: white;
    border: none;
  }
`;

export function MyPageEdit() {
  const { sessionUser } = SessionCurrent();

  let password;
  let name;
  let email;
  let birthday;

  async function EditBtnClick() {
    if (name) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/changeUserName",
          { userName: name } // userId 대신 userName을 사용
        );
        console.log(name);
      } catch (error) {
        console.log("요청에 실패했습니다.", error);
      }
    }
    if (name) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/changeUserName",
          { userId: sessionUser, userName: name }
        );
        const data = response.data;
        console.log(name);
      } catch (error) {
        console.log("요청에 실패했습니다.", error);
      }
    }
    if (email) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/changeUserEmail",
          { userId: sessionUser, email: email }
        );
        const data = response.data;
        console.log(email);
      } catch (error) {
        console.log("요청에 실패했습니다.", error);
      }
    }
    if (birthday) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/changeUserBirthday",
          { userId: sessionUser, birthday: birthday }
        );
        const data = response.data;
        console.log(birthday);
      } catch (error) {
        console.log("요청에 실패했습니다.", error);
      }
    }
  }

  return (
    <>
      <FlexC>
        <Title>Edit Info</Title>
        <Input
          type="text"
          placeholder="PW"
          onChange={(e) => {
            password = e.target.value;
          }}
        />
        <Input
          type="text"
          placeholder="NAME"
          onChange={(e) => {
            name = e.target.value;
          }}
        />
        <Input
          type="text"
          placeholder="EMAIL"
          onChange={(e) => {
            email = e.target.value;
          }}
        />
        <Input
          type="date"
          onChange={(e) => {
            birthday = e.target.value;
          }}
        />
        <Flex>
          <EditBtn
            onClick={() => {
              EditBtnClick();
            }}
          >
            Edit
          </EditBtn>
        </Flex>
      </FlexC>
    </>
  );
}
