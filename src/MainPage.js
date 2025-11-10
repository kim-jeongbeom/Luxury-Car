import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Search, Car, Globe, Calendar } from 'lucide-react';
import { brandService } from './services/api';
import Header from './components/Header';

function MainPage() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('전체');
  const [selectedCountry, setSelectedCountry] = useState('전체');
  const [brands, setBrands] = useState([]);
  const [filteredBrands, setFilteredBrands] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 백엔드에서 브랜드 데이터 가져오기
  useEffect(() => {
    const fetchBrands = async () => {
      try {
        setLoading(true);
        const data = await brandService.getAllBrands();
        setBrands(data);
        setError(null);
      } catch (err) {
        console.error('백엔드 연결 실패, fallback 데이터 사용:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchBrands();
  }, []);

  // 검색 및 필터링
  useEffect(() => {
    let filtered = brands;

    if (selectedCategory && selectedCategory !== '전체') {
      filtered = filtered.filter(brand => brand.category === selectedCategory);
    }

    if (selectedCountry && selectedCountry !== '전체') {
      filtered = filtered.filter(brand => brand.country === selectedCountry);
    }

    if (searchTerm) {
      filtered = filtered.filter(brand =>
        brand.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        brand.nameKr.includes(searchTerm) ||
        brand.country.includes(searchTerm)
      );
    }

    setFilteredBrands(filtered);
  }, [searchTerm, selectedCategory, selectedCountry, brands]);

  // 브랜드 클릭 시 상세 페이지로 이동
  const handleBrandClick = (brand) => {
    navigate(`/${brand.name.toLowerCase()}`);
  };

  return (
    <div className="min-h-screen bg-black">
      <div className="hero-gradient min-h-screen relative overflow-hidden">
        <div
          className="absolute inset-0 bg-cover bg-center bg-no-repeat opacity-30"
          style={{
            backgroundImage: 'url(/Logos/background.jpg)',
            filter: 'brightness(0.4)'
          }}
        />

        <Header />

        <div className="relative z-10 container mx-auto px-6 py-20">
          <div className="text-center mb-16 animate-fade-in">
            <div className="flex justify-center mb-8 animate-slide-up">
              <img
                src="/Logos/autoverse_main2.png"
                alt="AUTO VERSE"
                className="w-3/4 md:w-2/3 lg:w-1/2 h-auto object-contain"
              />
            </div>
            <p className="text-xl text-gray-300 max-w-2xl mx-auto leading-relaxed">
              세계 최고급 스포츠카와 럭셔리카 브랜드들의 특별한 컬렉션을 만나보세요
            </p>
            {error && (
              <div className="mt-4 p-3 bg-yellow-900/50 border border-yellow-400/30 rounded-lg text-yellow-200 text-sm max-w-md mx-auto">
                {error}
              </div>
            )}
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
              {['전체', '스포츠카', '럭셔리', '이탈리아', '영국', '독일', '프랑스', '스웨덴'].map((filter) => (
                <button
                  key={filter}
                  onClick={() => {
                    if (filter === '전체') {
                      setSelectedCategory('전체');
                      setSelectedCountry('전체');
                    } else if (['스포츠카', '럭셔리'].includes(filter)) {
                      setSelectedCategory(filter);
                      setSelectedCountry(null);
                    } else if (['이탈리아', '영국', '독일', '프랑스', '스웨덴', '미국', '일본'].includes(filter)) {
                      setSelectedCountry(filter);
                      setSelectedCategory(null);
                    }
                  }}
                  className={`px-6 py-3 rounded-full transition-all duration-300 font-medium ${
                    selectedCategory === filter || selectedCountry === filter
                      ? 'bg-gold-500 text-black shadow-lg scale-105'
                      : 'bg-gray-800/60 text-gray-300 hover:bg-gray-700/80 border border-gold-400/20'
                  }`}
                >
                  {filter}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>

      <div className="container mx-auto px-6 py-20">
        {loading ? (
          <div className="text-center py-20">
            <Car className="h-16 w-16 text-gold-400 mx-auto mb-4 animate-spin" />
            <p className="text-gold-400 text-xl">브랜드 데이터를 불러오는 중...</p>
          </div>
        ) : (
          <div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
              {filteredBrands.map((brand) => (
                <div
                  key={brand.id}
                  onClick={() => handleBrandClick(brand)}
                  className="bg-gray-900/60 backdrop-blur-md rounded-2xl overflow-hidden border border-gray-700/50 hover:border-gold-400/50 transition-all duration-300 hover:scale-105 hover:shadow-2xl hover:shadow-gold-400/20 group cursor-pointer"
                >
                  <div className="relative h-48 overflow-hidden bg-gray-700 flex items-center justify-center">
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
                    <div className="mb-3">
                      <h3 className="text-2xl font-bold text-white group-hover:text-gold-400 transition-colors duration-300">
                        {brand.name}
                      </h3>
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

            {filteredBrands.length === 0 && !loading && (
              <div className="text-center py-20">
                <Car className="h-16 w-16 text-gray-600 mx-auto mb-4" />
                <p className="text-gray-400 text-xl">검색 결과가 없습니다.</p>
              </div>
            )}
          </div>
        )}
      </div>

      <footer className="bg-gray-900 border-t border-gray-800">
        <div className="container mx-auto px-6 py-8">
          <div className="text-center">
            <h3 className="text-2xl font-bold gradient-text mb-2">AutoVerse</h3>
            <p className="text-gray-400">세계 최고급 자동차 브랜드 컬렉션</p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default MainPage;