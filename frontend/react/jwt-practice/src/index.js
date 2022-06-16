import React from 'react';
import ReactDOM from 'react-dom/client';           

import Root from './Root.js';
// import 'bootstrap/dist/css/bootstrap.css';

const rootNode = document.getElementById('root');

ReactDOM.createRoot(rootNode).render(
    <Root />,
);