import { useState } from "react";
import styled from "styled-components";
import { SessionCurrent } from "./SessionCurrent";
import axios from "axios";

const Container = styled.div`
  padding: 0 10%;
`;

const Profile = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;
const ImgBox = styled.div`
  width: 200px;
  height: 200px;
  border: 1px solid white;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 30px 0px;
`;
const Textarea = styled.textarea`
  width: 100%;
  padding: 5px;
  background-color: white;
  color: black;
  border: 1px solid white;
  margin: 30px 0px;
`;
const Button = styled.div`
  width: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
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

export function FeedCreate() {
  const [imageSrc, setImageSrc] = useState(null);
  const [imageByte, setImageByte] = useState(null);
  const [imageSendFile, setImageSendFile] = useState(null);
  const { sessionUser } = SessionCurrent();

  function showFile(file) {
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setImageSrc(imageUrl);
    }
  }

  function convertImageToBytes(event) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();

      reader.onload = () => {
        const arrayBuffer = reader.result;
        const byteArray = new Uint8Array(arrayBuffer);
        setImageByte(byteArray);
        convertBytesToFile(byteArray);
      };

      reader.onerror = () => {
        console.error("파일 읽기에 실패했습니다.");
      };

      reader.readAsArrayBuffer(file);
    }
  }

  function convertBytesToFile(byteArray) {
    const blob = new Blob([byteArray]);
    const file = new File([blob], "default_filename.bin");
    showFile(file);
    setImageSendFile(file);
  }

  async function editBtnClick() {
    if (imageSendFile != null) {
      const formData = new FormData();
      formData.append("file", imageSendFile);
      formData.append("user", sessionUser);
      formData.append("text", text);
      console.log(1, formData);
      try {
        const response = await axios.post(
          "http://localhost:8080/api/feed",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );
        console.log("img", response.data);
      } catch (error) {
        console.error(
          "요청에 실패했습니다.",
          error.response ? error.response.data : error.message
        );
      }
    } else {
      alert("이미지 파일을 업로드해주세요.");
    }
  }

  let text;

  return (
    <>
      <Container>
        <div
          style={{
            fontSize: "80px",
            fontWeight: "100",
            textAlign: "center",
            margin: "40px 0",
          }}
        >
          Feed Create
        </div>

        <Profile>
          <ImgBox>
            {imageSrc && (
              <img
                src={imageSrc}
                alt="Selected Image"
                style={{ maxHeight: "100%" }}
              />
            )}
          </ImgBox>
          <input
            type="file"
            id="fileInput"
            accept="image/*"
            onChange={(e) => {
              convertImageToBytes(e);
            }}
            required
          />
          <Textarea
            id="text"
            name="text"
            rows="5"
            placeholder="Enter Profile Text"
            required
            onChange={(e) => {
              text = e.target.value;
            }}
          />
        </Profile>
        <Button
          onClick={() => {
            editBtnClick();
          }}
        >
          Edit
        </Button>
      </Container>
    </>
  );
}
