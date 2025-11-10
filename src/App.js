import { Routes, Route } from 'react-router-dom';
import MainPage from './MainPage';
import BrandDetail from './BrandDetail';
import Login from './pages/Login';
import Signup from './pages/Signup';

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/:brandName" element={<BrandDetail />} />
    </Routes>
  );
}

export default App;
