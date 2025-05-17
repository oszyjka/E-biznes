import React, { useState } from 'react';
import axios from 'axios';

function Payments({ cartItems, clearCart }) {
  const [userName, setUserName] = useState('');
  const [message, setMessage] = useState('');

  const handlePayment = () => {
    if (!cartItems) {
      setMessage('Please select a product first.');
      return;
    }

    axios.post(`${import.meta.env.VITE_BACKEND_URL}/payments`, {
      items: cartItems,
      userName,
    })
      .then(() => {
        setMessage(`Payment successful for "${cartItems.length}" items!`);
        setUserName('');
        clearCart();
      })
      .catch((err) => {
        console.error('Payment failed:', err);
        setMessage('Payment failed. Please try again.');
      });
  };

  return (
    <div>
      <h2>Payments</h2>
      <input
        placeholder="Your name"
        value={userName}
        onChange={(user) => setUserName(user.target.value)}
      />
      <button onClick={handlePayment} disabled={!userName || cartItems.length == 0}>
        Pay for {cartItems.length} items
      </button>
      <p>{message}</p>
    </div>
  );
}

export default Payments;
