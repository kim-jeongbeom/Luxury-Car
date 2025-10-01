import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ArrowLeft, Car, Calendar, Zap, Gauge, DollarSign, Fuel } from 'lucide-react';
import { brandService, carService } from './services/api';

function BrandDetail() {
  const { brandName } = useParams();
  const navigate = useNavigate();
  const [brand, setBrand] = useState(null);
  const [cars, setCars] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadBrandAndCars = async () => {
      try {
        setLoading(true);

        // 모든 브랜드를 가져와서 URL의 브랜드명과 매칭
        const brands = await brandService.getAllBrands();
        const foundBrand = brands.find(
          b => b.name.toLowerCase() === brandName.toLowerCase()
        );

        if (!foundBrand) {
          navigate('/');
          return;
        }

        setBrand(foundBrand);

        // 해당 브랜드의 자동차 데이터 로드
        const carData = await carService.getCarsByBrandId(foundBrand.id);
        setCars(carData);
      } catch (err) {
        console.error('데이터 로드 실패:', err);
        setCars([]);
      } finally {
        setLoading(false);
      }
    };

    loadBrandAndCars();
  }, [brandName, navigate]);

  const handleBack = () => {
    navigate('/');
  };

  if (!brand) return null;

  return (
    <div className="min-h-screen bg-black">
      {/* Header */}
      <nav className="sticky top-0 z-50 bg-black/90 backdrop-blur-md border-b border-gray-800">
        <div className="container mx-auto px-6 py-4">
          <div className="flex items-center space-x-4">
            <button
              onClick={handleBack}
              className="flex items-center space-x-2 text-gold-400 hover:text-gold-300 transition-colors"
            >
              <ArrowLeft className="h-5 w-5" />
              <span>뒤로 가기</span>
            </button>
            <div className="h-6 w-px bg-gray-700"></div>
            <h1 className="text-xl font-bold text-white">{brand.name} 컬렉션</h1>
          </div>
        </div>
      </nav>

      {/* Brand Info */}
      <div className="bg-gradient-to-r from-gray-900 to-gray-800 py-16">
        <div className="container mx-auto px-6">
          <div className="flex flex-col md:flex-row items-center space-y-8 md:space-y-0 md:space-x-12">
            <div className="w-48 h-48 flex items-center justify-center">
              <img
                src={brand.image}
                alt={brand.name}
                className="max-w-full max-h-full object-contain p-4"
              />
            </div>
            <div className="flex-1 text-center md:text-left">
              <h2 className="text-4xl md:text-5xl font-bold text-white mb-4">{brand.name}</h2>
              <p className="text-xl text-gray-300 mb-4">{brand.nameKr}</p>
              <p className="text-gray-400 leading-relaxed max-w-3xl">{brand.description}</p>
              <div className="flex flex-wrap justify-center md:justify-start gap-6 mt-6 text-sm">
                <div className="flex items-center space-x-2">
                  <Calendar className="h-4 w-4 text-gold-400" />
                  <span className="text-gray-300">설립: {brand.founded}</span>
                </div>
                <div className="flex items-center space-x-2">
                  <span className="w-4 h-4 rounded-full bg-gold-400"></span>
                  <span className="text-gray-300">국가: {brand.country}</span>
                </div>
                <div className="flex items-center space-x-2">
                  <Car className="h-4 w-4 text-gold-400" />
                  <span className="text-gray-300">카테고리: {brand.category}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Cars Grid */}
      <div className="container mx-auto px-6 py-16">
        <h3 className="text-3xl font-bold text-white mb-8 text-center">모델 라인업</h3>

        {loading ? (
          <div className="text-center py-20">
            <Car className="h-16 w-16 text-gold-400 mx-auto mb-4 animate-spin" />
            <p className="text-gold-400 text-xl">모델 정보를 불러오는 중...</p>
          </div>
        ) : cars.length === 0 ? (
          <div className="text-center py-20">
            <Car className="h-16 w-16 text-gray-600 mx-auto mb-4" />
            <p className="text-gray-400 text-xl">등록된 모델이 없습니다.</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {cars.map((car) => (
              <div
                key={car.id}
                className="bg-gray-900/60 backdrop-blur-md rounded-2xl overflow-hidden border border-gray-700/50 hover:border-gold-400/50 transition-all duration-300 hover:scale-105 hover:shadow-2xl hover:shadow-gold-400/20 group"
              >
                <div className="relative h-72 overflow-hidden bg-gray-800">
                  <img
                    src={car.imageUrl}
                    alt={car.model}
                    className="w-full h-full object-cover transition-transform duration-300 group-hover:scale-110"
                  />
                  <div className="absolute top-4 right-4">
                    <span className="px-3 py-1 rounded-full text-xs font-medium bg-black/60 text-white">
                      {car.modelYear}
                    </span>
                  </div>
                </div>

                <div className="p-6">
                  <div className="mb-4">
                    <h4 className="text-xl font-bold text-white group-hover:text-gold-400 transition-colors duration-300">
                      {car.model}
                    </h4>
                    <p className="text-gold-400 font-medium">
                      ${car.price?.toLocaleString()} {car.priceCurrency}
                    </p>
                  </div>

                  <div className="grid grid-cols-2 gap-3 mb-4 text-sm">
                    <div className="flex items-center space-x-2">
                      <Zap className="h-4 w-4 text-red-400" />
                      <span className="text-gray-300">{car.horsepower}hp</span>
                    </div>
                    <div className="flex items-center space-x-2">
                      <Gauge className="h-4 w-4 text-blue-400" />
                      <span className="text-gray-300">{car.zeroToHundred}초</span>
                    </div>
                    <div className="flex items-center space-x-2">
                      <Fuel className="h-4 w-4 text-green-400" />
                      <span className="text-gray-300">{car.fuelType}</span>
                    </div>
                    <div className="flex items-center space-x-2">
                      <Car className="h-4 w-4 text-purple-400" />
                      <span className="text-gray-300">{car.bodyType}</span>
                    </div>
                  </div>

                  <div className="space-y-2 text-sm text-gray-400">
                    <div><span className="text-gray-300">엔진:</span> {car.engine}</div>
                    <div><span className="text-gray-300">구동:</span> {car.driveType}</div>
                    <div><span className="text-gray-300">색상:</span> {car.color}</div>
                  </div>

                  <div className="mt-4 pt-4 border-t border-gray-700/50">
                    <p className="text-gray-300 text-sm leading-relaxed">
                      {car.description}
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default BrandDetail;