import React, {useState} from 'react';
import axios from 'axios';

export default function Login({onLogin}){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [mode, setMode] = useState('login'); // login | signup

  const submit = async (e) => {
    e.preventDefault();
    try{
      if(mode === 'signup'){
        await axios.post('http://localhost:8080/api/auth/signup', {
          username, email: username+'@example.com', password
        });
      }
      const res = await axios.post('http://localhost:8080/api/auth/login', { username, password });
      const token = res.data.token;
      onLogin(token, username);
    }catch(err){
      alert('Auth error: ' + (err.response?.data?.message || err.message));
    }
  };

  return (
    <div style={{padding:20}}>
      <h3>{mode === 'login' ? 'Login' : 'Sign up'}</h3>
      <form onSubmit={submit}>
        <div>
          <input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} />
        </div>
        <div>
          <input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} />
        </div>
        <button type="submit">{mode === 'login' ? 'Login' : 'Sign up'}</button>
      </form>
      <hr />
      <button onClick={()=>setMode(mode==='login'?'signup':'login')}>Switch to {mode==='login'?'signup':'login'}</button>
    </div>
  );
}
