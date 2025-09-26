import { useState, useEffect } from 'react';
import { ArrowLeft, Car, Calendar, Zap, Gauge, DollarSign, Fuel } from 'lucide-react';

// 샘플 자동차 데이터
const sampleCars = {
  Ferrari: [
    {
      id: 1,
      model: 'F8 Tributo',
      year: 2023,
      price: 280000,
      currency: 'USD',
      engine: '3.9L Twin-Turbo V8',
      horsepower: 710,
      zeroToHundred: 2.9,
      maxSpeed: 340,
      fuelType: '가솔린',
      driveType: 'RWD',
      transmission: '7단 DCT',
      color: '로소 코르사',
      seats: 2,
      doors: 2,
      bodyType: '쿠페',
      description: '페라리의 대표적인 미드엔진 V8 슈퍼카로, 순수한 드라이빙 퍼포먼스를 추구합니다.',
      imageUrl: 'https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=600&h=400&fit=crop'
    },
    {
      id: 2,
      model: 'LaFerrari',
      year: 2022,
      price: 1500000,
      currency: 'USD',
      engine: '6.3L V12 + 전기모터',
      horsepower: 950,
      zeroToHundred: 2.4,
      maxSpeed: 370,
      fuelType: '하이브리드',
      driveType: 'RWD',
      transmission: '7단 DCT',
      color: '옐로우 모데나',
      seats: 2,
      doors: 2,
      bodyType: '쿠페',
      description: '페라리의 플래그십 하이퍼카로, 최첨단 하이브리드 기술과 F1 기술이 집약되었습니다.',
      imageUrl: 'https://images.unsplash.com/photo-1544829099-b9a0c5303bea?w=600&h=400&fit=crop'
    },
    {
      id: 3,
      model: '296 GTB',
      year: 2023,
      price: 320000,
      currency: 'USD',
      engine: '2.9L Twin-Turbo V6 + 전기모터',
      horsepower: 830,
      zeroToHundred: 2.9,
      maxSpeed: 330,
      fuelType: '하이브리드',
      driveType: 'RWD',
      transmission: '8단 DCT',
      color: '블루 코르사',
      seats: 2,
      doors: 2,
      bodyType: '쿠페',
      description: '페라리 최초의 V6 하이브리드 베를리네타로, 새로운 시대의 페라리를 대표합니다.',
      imageUrl: 'https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=600&h=400&fit=crop'
    }
  ],
  BMW: [
    {
      id: 4,
      model: 'M3 Competition',
      year: 2023,
      price: 85000,
      currency: 'USD',
      engine: '3.0L Twin-Turbo I6',
      horsepower: 510,
      zeroToHundred: 3.8,
      maxSpeed: 290,
      fuelType: '가솔린',
      driveType: 'RWD',
      transmission: '8단 자동',
      color: '알파인 화이트',
      seats: 5,
      doors: 4,
      bodyType: '세단',
      description: 'BMW M의 정수가 담긴 고성능 스포츠 세단으로, 트랙과 일상 모두에서 완벽한 성능을 제공합니다.',
      imageUrl: 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=600&h=400&fit=crop'
    },
    {
      id: 5,
      model: 'i8',
      year: 2022,
      price: 150000,
      currency: 'USD',
      engine: '1.5L Turbo I3 + 전기모터',
      horsepower: 374,
      zeroToHundred: 4.4,
      maxSpeed: 250,
      fuelType: '하이브리드',
      driveType: 'AWD',
      transmission: '6단 자동',
      color: '크리스탈 화이트',
      seats: 2,
      doors: 2,
      bodyType: '쿠페',
      description: 'BMW의 미래 지향적 하이브리드 스포츠카로, 혁신적인 디자인과 친환경 기술을 결합했습니다.',
      imageUrl: 'https://images.unsplash.com/photo-1617788138017-80ad40651399?w=600&h=400&fit=crop'
    }
  ],
  Porsche: [
    {
      id: 6,
      model: '911 Turbo S',
      year: 2023,
      price: 230000,
      currency: 'USD',
      engine: '3.8L Twin-Turbo Flat-6',
      horsepower: 650,
      zeroToHundred: 2.6,
      maxSpeed: 330,
      fuelType: '가솔린',
      driveType: 'AWD',
      transmission: '8단 PDK',
      color: '가드 레드',
      seats: 4,
      doors: 2,
      bodyType: '쿠페',
      description: '포르쉐 911의 최고 성능 버전으로, 완벽한 균형과 절대적인 성능을 자랑합니다.',
      imageUrl: 'https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=600&h=400&fit=crop'
    }
  ]
};

function BrandDetail({ brand, onBack }) {
  const [cars, setCars] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // 샘플 데이터 로드 (실제로는 API 호출)
    const loadCars = () => {
      setLoading(true);
      setTimeout(() => {
        setCars(sampleCars[brand?.name] || []);
        setLoading(false);
      }, 500);
    };

    if (brand) {
      loadCars();
    }
  }, [brand]);

  if (!brand) return null;

  return (
    <div className="min-h-screen bg-black">
      {/* Header */}
      <nav className="sticky top-0 z-50 bg-black/90 backdrop-blur-md border-b border-gray-800">
        <div className="container mx-auto px-6 py-4">
          <div className="flex items-center space-x-4">
            <button
              onClick={onBack}
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
            <div className="w-32 h-32 bg-white/10 rounded-2xl flex items-center justify-center">
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
                <div className="relative h-48 overflow-hidden">
                  <img
                    src={car.imageUrl}
                    alt={car.model}
                    className="w-full h-full object-cover transition-transform duration-300 group-hover:scale-110"
                  />
                  <div className="absolute top-4 right-4">
                    <span className="px-3 py-1 rounded-full text-xs font-medium bg-black/60 text-white">
                      {car.year}
                    </span>
                  </div>
                </div>

                <div className="p-6">
                  <div className="mb-4">
                    <h4 className="text-xl font-bold text-white group-hover:text-gold-400 transition-colors duration-300">
                      {car.model}
                    </h4>
                    <p className="text-gold-400 font-medium">
                      ${car.price.toLocaleString()} {car.currency}
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