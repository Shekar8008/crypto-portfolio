// Base URL (same origin since files are in static folder)
const BASE_URL = '';

// Fetch total value
fetch(`${BASE_URL}/api/holdings/value`)
  .then(res => res.json())
  .then(data => {
    // Adjust if your JSON is just a number or has a field
    const total = data.totalValue ?? data;
    document.getElementById('total-value').textContent = `Total Market Value: ₹${total}`;
  })
  .catch(err => {
    document.getElementById('total-value').textContent = 'Failed to load total value';
    console.error('Error fetching total value:', err);
  });

// Fetch holdings list
fetch(`${BASE_URL}/api/holdings/list`)
  .then(res => res.json())
  .then(list => {
    const tbody = document.querySelector('#holdings-table tbody');
    tbody.innerHTML = '';
    list.forEach(coin => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${coin.name ?? coin.symbol}</td>
        <td>${coin.quantity}</td>
        <td>${coin.buyPrice}</td>
        <td>${coin.livePrice}</td>
        <td>${coin.marketValue}</td>
        <td class="${(coin.profitLoss >= 0) ? 'profit' : 'loss'}">${coin.profitLoss}</td>
      `;
      tbody.appendChild(row);
    });
  })
  .catch(err => {
    console.error('Error fetching holdings list:', err);
  });
