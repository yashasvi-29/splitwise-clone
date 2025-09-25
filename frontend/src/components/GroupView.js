import React, {useEffect, useState} from 'react';
import axios from 'axios';
import ExpenseForm from './ExpenseForm';

export default function GroupView({group}){
  const [expenses, setExpenses] = useState([]);
  useEffect(()=>{
    axios.get(`http://localhost:8080/api/expenses/group/${group.id}`)
      .then(res=>setExpenses(res.data))
      .catch(err=>console.error(err));
  }, [group]);
  return (
    <div style={{border:'1px solid #ddd', padding:10, marginTop:10}}>
      <h4>{group.name}</h4>
      <ExpenseForm group={group} />
      <h5>Expenses</h5>
      <ul>
        {expenses.map(e=>(
          <li key={e.id}>{e.description} — {e.amount} (payer: {e.payer?.username})</li>
        ))}
      </ul>
    </div>
  );
}
