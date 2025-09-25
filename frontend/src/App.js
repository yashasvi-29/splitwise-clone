import React, {useState} from 'react';
import Login from './components/Login';
import Dashboard from './components/Dashboard';

function App(){
  const [token, setToken] = useState(null);
  const [username, setUsername] = useState(null);

  if(!token){
    return <Login onLogin={(t,u)=>{setToken(t); setUsername(u);}} />;
  }
  return <Dashboard token={token} username={username} />;
}

export default App;
