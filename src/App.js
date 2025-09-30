import { Routes, Route } from 'react-router-dom';
import MainPage from './MainPage';
import BrandDetail from './BrandDetail';

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/:brandName" element={<BrandDetail />} />
    </Routes>
  );
}

export default App;
