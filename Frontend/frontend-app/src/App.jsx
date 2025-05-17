import React, { useState } from 'react';
import Products from './components/Products';
import Payments from './components/Payments';

function App() {
  const [selectedProduct, setSelectedProduct] = useState(null);

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Shop</h1>
      <Products onSelect={setSelectedProduct} />
      <hr />
      <Payments product={selectedProduct} />
    </div>
  );
}

export default App;
