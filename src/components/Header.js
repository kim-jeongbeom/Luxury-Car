import { useNavigate } from 'react-router-dom';
import { Car, LogOut, UserCircle } from 'lucide-react';
import { useAuth } from '../contexts/AuthContext';

const Header = () => {
  const navigate = useNavigate();
  const { user, logout, isAuthenticated } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav className="sticky top-0 z-50 bg-black/80 backdrop-blur-md border-b border-gray-800">
      <div className="w-full px-1">
        <div className="flex items-center justify-between">
          <img
            src="/Logos/autoverse_header.png"
            alt="AutoVerse"
            className="h-24 w-auto cursor-pointer transition-opacity"
            onClick={() => navigate('/')}
          />
          <div className="flex items-center space-x-6 pr-6">
            {isAuthenticated ? (
              <div className="flex items-center space-x-4">
                <button
                  onClick={handleLogout}
                  className="transition-opacity hover:opacity-70"
                >
                  <LogOut className="h-7 w-7 text-gold-400" />
                </button>
                <span className="text-gray-300">{user.nickname}님</span>
              </div>
            ) : (
              <button
                onClick={() => navigate('/login')}
                className="transition-opacity"
              >
                <UserCircle className="h-9 w-9 text-gold-400" />
              </button>
            )}
            <Car className="h-8 w-8 text-gold-400" />
            <span className="text-gold-400 font-medium">Premium Collection</span>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Header;
