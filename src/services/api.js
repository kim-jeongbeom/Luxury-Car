const API_BASE_URL = 'http://localhost:8080/api';

// Brand API functions
export const brandService = {
  getAllBrands: async () => {
    const response = await fetch(`${API_BASE_URL}/brands`);
    if (!response.ok) {
      throw new Error('Failed to fetch brands');
    }
    return response.json();
  },

  getBrandById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/brands/${id}`);
    if (!response.ok) {
      throw new Error('Failed to fetch brand');
    }
    return response.json();
  },

  createBrand: async (brandData) => {
    const response = await fetch(`${API_BASE_URL}/brands`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(brandData),
    });
    if (!response.ok) {
      throw new Error('Failed to create brand');
    }
    return response.json();
  }
};

// Car API functions
export const carService = {
  getAllCars: async () => {
    const response = await fetch(`${API_BASE_URL}/cars`);
    if (!response.ok) {
      throw new Error('Failed to fetch cars');
    }
    return response.json();
  },

  getCarById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/cars/${id}`);
    if (!response.ok) {
      throw new Error('Failed to fetch car');
    }
    return response.json();
  },

  getCarsByBrandId: async (brandId) => {
    const response = await fetch(`${API_BASE_URL}/cars/brand/${brandId}`);
    if (!response.ok) {
      throw new Error('Failed to fetch cars by brand');
    }
    return response.json();
  },

  searchCars: async (model) => {
    const response = await fetch(`${API_BASE_URL}/cars/search?model=${encodeURIComponent(model)}`);
    if (!response.ok) {
      throw new Error('Failed to search cars');
    }
    return response.json();
  },

  filterCars: async (filters) => {
    const params = new URLSearchParams();
    Object.entries(filters).forEach(([key, value]) => {
      if (value !== null && value !== undefined && value !== '') {
        params.append(key, value);
      }
    });

    const response = await fetch(`${API_BASE_URL}/cars/filter?${params}`);
    if (!response.ok) {
      throw new Error('Failed to filter cars');
    }
    return response.json();
  },

  createCar: async (carData) => {
    const response = await fetch(`${API_BASE_URL}/cars`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(carData),
    });
    if (!response.ok) {
      throw new Error('Failed to create car');
    }
    return response.json();
  },

  updateCar: async (id, carData) => {
    const response = await fetch(`${API_BASE_URL}/cars/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(carData),
    });
    if (!response.ok) {
      throw new Error('Failed to update car');
    }
    return response.json();
  },

  deleteCar: async (id) => {
    const response = await fetch(`${API_BASE_URL}/cars/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) {
      throw new Error('Failed to delete car');
    }
  }
};