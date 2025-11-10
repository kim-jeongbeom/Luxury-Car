import React, { createContext, useState, useContext, useEffect } from 'react';
import { authService } from '../services/api';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in on mount
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');

    if (token && userData) {
      setUser(JSON.parse(userData));
    }
    setLoading(false);
  }, []);

  const login = async (userId, password) => {
    try {
      const response = await authService.login({ userId, password });

      localStorage.setItem('token', response.token);
      localStorage.setItem('user', JSON.stringify({
        userId: response.userId,
        name: response.name,
        phoneNumber: response.phoneNumber,
        nickname: response.nickname,
        role: response.role
      }));

      setUser({
        userId: response.userId,
        name: response.name,
        phoneNumber: response.phoneNumber,
        nickname: response.nickname,
        role: response.role
      });

      return { success: true };
    } catch (error) {
      return {
        success: false,
        error: error.message || 'Login failed'
      };
    }
  };

  const signup = async (userId, password, name, phoneNumber, nickname) => {
    try {
      const response = await authService.signup({ userId, password, name, phoneNumber, nickname });

      localStorage.setItem('token', response.token);
      localStorage.setItem('user', JSON.stringify({
        userId: response.userId,
        name: response.name,
        phoneNumber: response.phoneNumber,
        nickname: response.nickname,
        role: response.role
      }));

      setUser({
        userId: response.userId,
        name: response.name,
        phoneNumber: response.phoneNumber,
        nickname: response.nickname,
        role: response.role
      });

      return { success: true };
    } catch (error) {
      return {
        success: false,
        error: error.message || 'Signup failed'
      };
    }
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  const value = {
    user,
    login,
    signup,
    logout,
    loading,
    isAuthenticated: !!user
  };

  return (
    <AuthContext.Provider value={value}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
