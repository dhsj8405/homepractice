import React, { useState, useEffect,useRef } from 'react';

import {
    Form,
    Input,
    InputGroup,

} from 'reactstrap';
import axios from 'axios'


const LoginIndex = () => {
const [loginUser, setLoginUser] = useState({
    id: "",
    pwd: "",
});
const [userInfo, setUserInfo] = useState({
    id: "",
    pwd: "",
    name: "",

});

  useEffect(() => {
    localStorage.setItem('jwtToken', '');
  }, []);

/*
 *  아이디 비번 입력 
 */

    //아이디 입력 핸들러
    const onChangeInputId = (e) => {
        setLoginUser( Object.assign({}, loginUser, { id: e.target.value }))
    };
    //비밀번호 입력 핸들러
    const onChangeInputPwd = (e) => {
        setLoginUser( Object.assign({}, loginUser, { pwd: e.target.value }))
    }
     
    // 로그인 버튼 핸들러
    const onClickLogin = (e,user) => {
        console.log(user)
        
        e.preventDefault();

        axios({
            url: 'http://localhost:9099/login/login',
            method: 'post',
            data: user
        }).then((res)=> {
            console.log(res.data);  
            localStorage.setItem('jwtToken',res.data);
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data;
            console.log(localStorage.getItem('jwtToken'));
            
        }).catch((error)=>{
            console.log("######로그인버튼 클릭시 에러####")
            console.log(error)
        })

    }

    const onClickAuth = (e,user) => {
        e.preventDefault();
            axios({
                url: 'http://localhost:9099/login/auth',
                method: 'post',
                data: user
            }).then((res)=> {
                console.log(res.data);  
                
                
            }).catch((error)=>{
                console.log("######권한요청 클릭시 에러####")
                
            })
    }

    return (
        <div className='center-content'>
  
     
            {/* <InputGroup className="input-group-alternative"> */}
            <Form className='login-form'>
                <InputGroup>
                    <Input 
                        type="id" 
                        value={loginUser.id} 
                        placeholder='아이디'
                        onChange={onChangeInputId}
                    />
                </InputGroup>
                <br/>
                <InputGroup>
                    <Input 
                        type="password" 
                        value={loginUser.pwd}
                        onChange={onChangeInputPwd}
                        placeholder='비밀번호' />
                </InputGroup>
            </Form>
            <button onClick={(e)=> onClickLogin(e,loginUser)}>로그인하기</button>
            <button onClick={(e)=> onClickAuth(e,loginUser)}>권한요청</button>
        </div>
    );
};

export default LoginIndex;