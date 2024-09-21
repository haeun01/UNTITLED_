import { BrowserRouter, Route, Routes } from "react-router-dom";
import styled from "styled-components";
import { Sidebar } from "./Sidebar";
import { Intro } from "./Intro";
import { Login } from "./Login";
import { SignUp } from "./Signup";

const Container = styled.div`
  width: 100vw;
  justify-content: center;
`;
const Section = styled.div`
  width: 100%;
`;
const Nav = styled.div`
  width: 100%;
  background-color: white;

  display: flex;
  justify-content: space-around;
  align-items: center;
`;
const ContentBox = styled.div`
  width: 100%;
`;

export function MainPage() {
  return (
    <>
      <BrowserRouter>
        <Container>
          <Section>
            <Nav>
              <Sidebar />
            </Nav>
            <ContentBox>
              <Routes>
                <Route path="/" element={<Intro />} />
                <Route path="/intro" element={<Intro />} />
                {/* <Route path="/feed" element={<Feed />} /> */}
                {/* <Route path="/mypage" element={<Mypage />} /> */}
                {/* <Route path="/lecture" element={<LectureWrapper />}>
                  <Route index element={<Lecture />} />
                  <Route path=":id" element={<Streaming />}></Route>
                </Route> */}
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<SignUp />} />
              </Routes>
            </ContentBox>
          </Section>
        </Container>
      </BrowserRouter>
    </>
  );
}
