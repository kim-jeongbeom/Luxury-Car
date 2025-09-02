import { useState, useEffect } from 'react';
import { Search, Car, Globe, Calendar, Star } from 'lucide-react';

const brandsData = [
  {
    id: 1,
    name: 'Ferrari',
    nameKr: '페라리',
    founded: 1939,
    country: '이탈리아',
    category: '스포츠카',
    description: '이탈리아의 전설적인 스포츠카 브랜드로, 레이싱의 열정과 최고급 성능을 자랑합니다.',
    representative: 'F8 Tributo, LaFerrari',
    image: '/Logos/Ferrari.svg'
  },
  {
    id: 2,
    name: 'Lamborghini',
    nameKr: '람보르기니',
    founded: 1963,
    country: '이탈리아',
    category: '스포츠카',
    description: '황소 로고로 유명한 이탈리아 슈퍼카 브랜드, 공격적인 디자인과 강력한 성능으로 유명합니다.',
    representative: 'Huracán, Aventador',
    image: '/Logos/Lamborghini.svg'
  },
  {
    id: 3,
    name: 'Rolls-Royce',
    nameKr: '롤스로이스',
    founded: 1904,
    country: '영국',
    category: '럭셔리',
    description: '영국 왕실이 사랑하는 최고급 럭셔리카 브랜드, 수작업으로 제작되는 극상의 품질을 자랑합니다.',
    representative: 'Phantom, Ghost',
    image: '/Logos/ROLLS-ROYCE.svg'
  },
  {
    id: 4,
    name: 'Bentley',
    nameKr: '벤틀리',
    founded: 1919,
    country: '영국',
    category: '럭셔리',
    description: '영국의 프리미엄 럭셔리 브랜드로, 전통적인 장인정신과 현대적 기술의 완벽한 조화를 보여줍니다.',
    representative: 'Continental GT, Bentayga',
    image: '/Logos/Bentley.svg'
  },
  {
    id: 5,
    name: 'McLaren',
    nameKr: '맥라렌',
    founded: 1963,
    country: '영국',
    category: '스포츠카',
    description: 'F1에서 검증된 기술력으로 제작되는 영국의 슈퍼카 브랜드, 혁신적인 엔지니어링이 특징입니다.',
    representative: '720S, P1',
    image: '/Logos/McLaren.svg'
  },
  {
    id: 6,
    name: 'Porsche',
    nameKr: '포르쉐',
    founded: 1931,
    country: '독일',
    category: '스포츠카',
    description: '독일의 대표적인 스포츠카 브랜드로, 911 시리즈로 유명하며 정밀한 독일 엔지니어링의 대명사입니다.',
    representative: '911, Cayenne',
    image: '/Logos/Porsche.svg'
  },
  {
    id: 7,
    name: 'Aston-Martin',
    nameKr: '애스턴마틴',
    founded: 1913,
    country: '영국',
    category: '럭셔리',
    description: '1913년 설립된 영국의 프리미엄 스포츠카 브랜드로, 제임스 본드 시리즈와 F1 레이싱으로 유명한 럭셔리 자동차 제조사입니다.',
    representative: 'DB11, DBX, 밴티지',
    image: '/Logos/Aston Martin.svg'
  },
];

function App() {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('전체');
  const [filteredBrands, setFilteredBrands] = useState(brandsData);

  useEffect(() => {
    let filtered = brandsData;
    
    if (selectedCategory !== '전체') {
      filtered = filtered.filter(brand => brand.category === selectedCategory);
    }
    
    if (searchTerm) {
      filtered = filtered.filter(brand => 
        brand.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        brand.nameKr.includes(searchTerm) ||
        brand.country.includes(searchTerm)
      );
    }
    
    setFilteredBrands(filtered);
  }, [searchTerm, selectedCategory]);

  return (
    <div className="min-h-screen bg-black">
      <div className="hero-gradient min-h-screen relative overflow-hidden">
        <div 
          className="absolute inset-0 bg-cover bg-center bg-no-repeat opacity-20"
          style={{
            backgroundImage: 'url(https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?w=1920&h=1080&fit=crop)'
          }}
        />
        
        <nav className="sticky top-0 z-50 bg-black/80 backdrop-blur-md">
          <div className="container mx-auto px-6 py-4">
            <div className="flex items-center justify-between">
              <h1 className="text-2xl font-bold gradient-text">Luxury Cars</h1>
              <div className="flex items-center space-x-6">
                <Car className="h-6 w-6 text-gold-400" />
                <span className="text-gold-400 font-medium">Premium Collection</span>
              </div>
            </div>
          </div>
        </nav>

        <div className="relative z-10 container mx-auto px-6 py-20">
          <div className="text-center mb-16 animate-fade-in">
            <h1 className="text-6xl md:text-8xl font-bold mb-6 gradient-text animate-slide-up">
              LUXURY
            </h1>
            <h2 className="text-4xl md:text-6xl font-light mb-8 text-white">
              SPORTS CAR BRANDS
            </h2>
            <p className="text-xl text-gray-300 max-w-2xl mx-auto leading-relaxed">
              세계 최고급 스포츠카와 럭셔리카 브랜드들의 특별한 컬렉션을 만나보세요
            </p>
          </div>

          <div className="max-w-4xl mx-auto mb-12">
            <div className="relative mb-8">
              <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gold-400 h-5 w-5" />
              <input
                type="text"
                placeholder="브랜드명, 국가로 검색..."
                className="w-full pl-12 pr-4 py-4 bg-gray-900/80 backdrop-blur-md border border-gold-400/30 rounded-xl text-white placeholder-gray-400 focus:outline-none focus:border-gold-400 focus:ring-2 focus:ring-gold-400/20 transition-all duration-300"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            
            <div className="flex flex-wrap justify-center gap-4">
              {['전체', '스포츠카', '럭셔리'].map((category) => (
                <button
                  key={category}
                  onClick={() => setSelectedCategory(category)}
                  className={`px-6 py-3 rounded-full transition-all duration-300 font-medium ${
                    selectedCategory === category
                      ? 'bg-gold-500 text-black shadow-lg scale-105'
                      : 'bg-gray-800/60 text-gray-300 hover:bg-gray-700/80 border border-gold-400/20'
                  }`}
                >
                  {category}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>

      <div className="container mx-auto px-6 py-20">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {filteredBrands.map((brand) => (
            <div
              key={brand.id}
              className="bg-gray-900/60 backdrop-blur-md rounded-2xl overflow-hidden border border-gray-700/50 hover:border-gold-400/50 transition-all duration-300 hover:scale-105 hover:shadow-2xl hover:shadow-gold-400/20 group"
            >
              <div className="relative h-48 overflow-hidden bg-gray-800 flex items-center justify-center">
                <img
                  src={brand.image}
                  alt={brand.name}
                  className="max-w-full max-h-full object-contain p-4 transition-transform duration-300 group-hover:scale-110"
                />
                <div className="absolute top-4 right-4">
                  <span className={`px-3 py-1 rounded-full text-xs font-medium ${
                    brand.category === '스포츠카' 
                      ? 'bg-red-500/20 text-red-400 border border-red-400/30'
                      : 'bg-gold-500/20 text-gold-400 border border-gold-400/30'
                  }`}>
                    {brand.category}
                  </span>
                </div>
              </div>
              
              <div className="p-6">
                <div className="flex items-center justify-between mb-3">
                  <h3 className="text-2xl font-bold text-white group-hover:text-gold-400 transition-colors duration-300">
                    {brand.name}
                  </h3>
                  <Star className="h-5 w-5 text-gold-400 opacity-0 group-hover:opacity-100 transition-opacity duration-300" />
                </div>
                <p className="text-gray-400 text-lg mb-4">{brand.nameKr}</p>
                
                <div className="flex items-center space-x-6 mb-4 text-sm text-gray-400">
                  <div className="flex items-center space-x-2">
                    <Calendar className="h-4 w-4" />
                    <span>{brand.founded}</span>
                  </div>
                  <div className="flex items-center space-x-2">
                    <Globe className="h-4 w-4" />
                    <span>{brand.country}</span>
                  </div>
                </div>
                
                <p className="text-gray-300 text-sm leading-relaxed mb-4">
                  {brand.description}
                </p>
                
                <div className="pt-4 border-t border-gray-700/50">
                  <p className="text-xs text-gray-400 mb-1">대표 모델</p>
                  <p className="text-gold-400 font-medium text-sm">{brand.representative}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
        
        {filteredBrands.length === 0 && (
          <div className="text-center py-20">
            <Car className="h-16 w-16 text-gray-600 mx-auto mb-4" />
            <p className="text-gray-400 text-xl">검색 결과가 없습니다.</p>
          </div>
        )}
      </div>
      
      <footer className="bg-gray-900 border-t border-gray-800">
        <div className="container mx-auto px-6 py-8">
          <div className="text-center">
            <h3 className="text-2xl font-bold gradient-text mb-2">Luxury Car Brands</h3>
            <p className="text-gray-400">세계 최고급 자동차 브랜드 컬렉션</p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default App;
