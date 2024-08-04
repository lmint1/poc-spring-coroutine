import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 10 }, // Ramp-up to 10 users over 1 minute
    { duration: '30s', target: 10 }, // Stay at 10 users for 2 minutes
    { duration: '0', target: 0 },  // Ramp-down to 0 users over 1 minute
  ],
};

export default function () {
  // Send a GET request to the API endpoint
  const res = http.get('http://localhost:8080/api/suspended');

  // Check that the response status is 200
  check(res, {
    'is status 200': (r) => r.status === 200,
  });

  // Sleep for 1 second between requests to simulate a more realistic load
  sleep(1);
}
