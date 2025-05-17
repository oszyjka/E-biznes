import React, { useState } from 'react';
import axios from 'axios';

function Payments({ product }) {
  const [userName, setUserName] = useState('');
  const [message, setMessage] = useState('');

  const handlePayment = () => {
    if (!product) {
      setMessage('Please select a product first.');
      return;
    }

    axios.post('/payments', {
      productID: product.ID,
      userName,
    })
      .then(() => {
        setMessage(`Payment successful for "${product.name}"!`);
        setUserName('');
      })
      .catch((err) => {
        console.error('Payment failed:', err);
        setMessage('Payment failed. Please try again.');
      });
  };

  return (
    <div>
      <h2>Payments</h2>
      <p>Selected product: <strong>{product ? product.name : 'None'}</strong></p>
      <input
        placeholder="Your name"
        value={userName}
        onChange={(user) => setUserName(user.target.value)}
      />
      <button onClick={handlePayment} disabled={!userName || !product}>
        Pay
      </button>
      <p>{message}</p>
    </div>
  );
}

export default Payments;
