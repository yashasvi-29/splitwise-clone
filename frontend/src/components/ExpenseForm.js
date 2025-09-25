import React, {useState} from 'react';
import axios from 'axios';

export default function ExpenseForm({group}){
  const [desc, setDesc] = useState('');
  const [amount, setAmount] = useState('');
  const [payerId, setPayerId] = useState(null);

  const submit = async (e) => {
    e.preventDefault();
    // simple equal split among group members (frontend doesn't know member list in scaffold)
    const splits = [{ userId: group.owner?.id || 1, amount: Number(amount) }];
    try{
      await axios.post('http://localhost:8080/api/expenses', {
        description: desc, amount: Number(amount), payerId: group.owner?.id || 1, groupId: group.id, splits
      });
      alert('Expense added (refresh to see).');
    }catch(err){
      alert('Error: ' + err.message);
    }
  };

  return (
    <form onSubmit={submit} style={{marginBottom:10}}>
      <input placeholder="description" value={desc} onChange={e=>setDesc(e.target.value)} />
      <input placeholder="amount" value={amount} onChange={e=>setAmount(e.target.value)} />
      <button type="submit">Add Expense</button>
    </form>
  );
}
