import React from 'react';

function ListofIndianPlayers({ IndianPlayers }) {
  return (
    <div>
      {IndianPlayers.map((item, index) => (
        <li key={index}>Mr. {item}</li>
      ))}
    </div>
  );
}

export default ListofIndianPlayers;
