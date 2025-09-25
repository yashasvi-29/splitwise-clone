import React, {useEffect, useState} from 'react';
import axios from 'axios';
import GroupView from './GroupView';

export default function Dashboard({token, username}){
  const [groups, setGroups] = useState([]);
  const [selected, setSelected] = useState(null);
  useEffect(()=>{
    axios.get('http://localhost:8080/api/groups')
      .then(res=>setGroups(res.data))
      .catch(err=>console.error(err));
  }, []);
  return (
    <div style={{padding:20}}>
      <h2>Welcome, {username}</h2>
      <h3>Groups</h3>
      <ul>
        {groups.map(g=>(<li key={g.id}>
          <a href="#" onClick={(e)=>{e.preventDefault(); setSelected(g);}}>{g.name}</a>
        </li>))}
      </ul>
      {selected && <GroupView group={selected} />}
    </div>
  );
}
