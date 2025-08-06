import React from 'react';

function Scorebelow70({ players }) {
  const players70 = [];
  players.map(item => {
    if (item.score <= 70) {
      players70.push(item);
    }
    return null;
  });

  return (
    <div>
      {players70.map((player, i) => (
        <li key={i}>
          Mr. {player.name} <span>{player.score}</span>
        </li>
      ))}
    </div>
  );
}

export default Scorebelow70;

