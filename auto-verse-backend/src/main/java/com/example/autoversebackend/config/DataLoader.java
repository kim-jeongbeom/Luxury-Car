package com.example.autoversebackend.config;

import com.example.autoversebackend.brand.entity.BrandEntity;
import com.example.autoversebackend.brand.repository.BrandRepository;
import com.example.autoversebackend.car.entity.CarEntity;
import com.example.autoversebackend.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0) {
            loadInitialBrands();
        }
        // 자동차 초기 데이터 로드 비활성화 (수동 등록만 사용)
        // if (carRepository.count() == 0) {
        //     loadInitialCars();
        // }
    }

    private void loadInitialBrands() {
        List<BrandEntity> brands = Arrays.asList(
            BrandEntity.builder()
                .name("Ferrari")
                .nameKr("페라리")
                .founded(1939)
                .country("이탈리아")
                .category("스포츠카")
                .description("이탈리아의 전설적인 스포츠카 브랜드로, 레이싱의 열정과 최고급 성능을 자랑합니다.")
                .representative("296 GTB, Roma, SF90 Stradale")
                .image("/Logos/Ferrari.svg")
                .build(),
            BrandEntity.builder()
                .name("Lamborghini")
                .nameKr("람보르기니")
                .founded(1963)
                .country("이탈리아")
                .category("스포츠카")
                .description("황소 로고로 유명한 이탈리아 슈퍼카 브랜드, 공격적인 디자인과 강력한 성능으로 유명합니다.")
                .representative("Revuelto, Huracán Tecnica, Urus Performante")
                .image("/Logos/Lamborghini.svg")
                .build(),
            BrandEntity.builder()
                .name("Rolls-Royce")
                .nameKr("롤스로이스")
                .founded(1904)
                .country("영국")
                .category("럭셔리")
                .description("영국 왕실이 사랑하는 최고급 럭셔리카 브랜드, 수작업으로 제작되는 극상의 품질을 자랑합니다.")
                .representative("Phantom, Ghost, Cullinan")
                .image("/Logos/ROLLS-ROYCE.svg")
                .build(),
            BrandEntity.builder()
                .name("Bentley")
                .nameKr("벤틀리")
                .founded(1919)
                .country("영국")
                .category("럭셔리")
                .description("영국의 프리미엄 럭셔리 브랜드로, 전통적인 장인정신과 현대적 기술의 완벽한 조화를 보여줍니다.")
                .representative("Continental GT Speed, Flying Spur, Bentayga EWB")
                .image("/Logos/Bentley.svg")
                .build(),
            BrandEntity.builder()
                .name("McLaren")
                .nameKr("맥라렌")
                .founded(1963)
                .country("영국")
                .category("스포츠카")
                .description("F1에서 검증된 기술력으로 제작되는 영국의 슈퍼카 브랜드, 혁신적인 엔지니어링이 특징입니다.")
                .representative("750S, Artura, GT")
                .image("/Logos/McLaren.svg")
                .build(),
            BrandEntity.builder()
                .name("Porsche")
                .nameKr("포르쉐")
                .founded(1931)
                .country("독일")
                .category("스포츠카")
                .description("독일의 대표적인 스포츠카 브랜드로, 911 시리즈로 유명하며 정밀한 독일 엔지니어링의 대명사입니다.")
                .representative("911 Turbo S, 718 Cayman GT4 RS, Taycan Turbo S")
                .image("/Logos/Porsche.svg")
                .build(),
            BrandEntity.builder()
                .name("Aston Martin")
                .nameKr("애스턴마틴")
                .founded(1913)
                .country("영국")
                .category("럭셔리")
                .description("1913년 설립된 영국의 프리미엄 스포츠카 브랜드로, 제임스 본드 시리즈와 F1 레이싱으로 유명한 럭셔리 자동차 제조사입니다.")
                .representative("DB12, DBX707, Vantage")
                .image("/Logos/Aston Martin.webp")
                .build(),
            BrandEntity.builder()
                .name("Mercedes-Benz")
                .nameKr("메르세데스-벤츠")
                .founded(1926)
                .country("독일")
                .category("럭셔리")
                .description("독일의 대표적인 프리미엄 자동차 브랜드로, AMG 라인으로 스포츠카 영역까지 아우르는 종합 럭셔리 브랜드입니다.")
                .representative("AMG GT Coupe, SLS AMG, CLS 450 4MATIC Coupe")
                .image("/Logos/Benz.svg")
                .build(),
            BrandEntity.builder()
                .name("BMW")
                .nameKr("BMW")
                .founded(1916)
                .country("독일")
                .category("럭셔리")
                .description("바이에른의 엔진 제조업체에서 시작된 독일 프리미엄 브랜드로, M 시리즈로 고성능 스포츠카도 제조합니다.")
                .representative("7 Series, M3, i8")
                .image("/Logos/BMW.png")
                .build(),
            BrandEntity.builder()
                .name("Audi")
                .nameKr("아우디")
                .founded(1910)
                .country("독일")
                .category("럭셔리")
                .description("네 개의 링으로 상징되는 독일 프리미엄 브랜드로, 콰트로 시스템과 RS 라인으로 유명합니다.")
                .representative("A8, R8, RS6")
                .image("/Logos/Audi.webp")
                .build(),
            BrandEntity.builder()
                .name("Bugatti")
                .nameKr("부가티")
                .founded(1909)
                .country("프랑스")
                .category("스포츠카")
                .description("프랑스의 극초고성능 하이퍼카 브랜드로, 1000마력 이상의 엔진과 최고 속도 기록으로 유명합니다.")
                .representative("Chiron, Veyron")
                .image("/Logos/Bugatti.svg")
                .build(),
            BrandEntity.builder()
                .name("Koenigsegg")
                .nameKr("코닉세그")
                .founded(1994)
                .country("스웨덴")
                .category("스포츠카")
                .description("스웨덴의 극초고성능 하이퍼카 제조업체로, 혁신적인 엔지니어링과 최첨단 기술력을 자랑합니다.")
                .representative("Regera, Jesko")
                .image("/Logos/Koenigsegg.png")
                .build(),
            BrandEntity.builder()
                .name("Pagani")
                .nameKr("파가니")
                .founded(1992)
                .country("이탈리아")
                .category("스포츠카")
                .description("이탈리아의 수제 하이퍼카 브랜드로, 예술품 수준의 디자인과 카본파이버 기술의 장인정신을 보여줍니다.")
                .representative("Huayra, Zonda")
                .image("/Logos/Pagani.webp")
                .build(),
            BrandEntity.builder()
                .name("Maserati")
                .nameKr("마세라티")
                .founded(1914)
                .country("이탈리아")
                .category("럭셔리")
                .description("삼지창 로고의 이탈리아 럭셔리 스포츠카 브랜드로, 우아함과 성능을 동시에 추구하는 그란투리스모의 대명사입니다.")
                .representative("Quattroporte, GranTurismo")
                .image("/Logos/Maserati.png")
                .build()
        );

        brandRepository.saveAll(brands);
        System.out.println("초기 브랜드 데이터 " + brands.size() + "개가 성공적으로 로드되었습니다.");
    }

    private void loadInitialCars() {
        // 브랜드들을 먼저 조회
        BrandEntity ferrari = brandRepository.findByName("Ferrari").orElse(null);
        BrandEntity bmw = brandRepository.findByName("BMW").orElse(null);
        BrandEntity porsche = brandRepository.findByName("Porsche").orElse(null);

        if (ferrari == null || bmw == null || porsche == null) {
            System.out.println("브랜드 데이터가 없어서 자동차 데이터를 로드할 수 없습니다.");
            return;
        }

        List<CarEntity> cars = Arrays.asList(
            // Ferrari 자동차들
            CarEntity.builder()
                .brand(ferrari)
                .model("F8 Tributo")
                .modelYear(2023)
                .price(new BigDecimal("280000"))
                .priceCurrency("USD")
                .engine("3.9L Twin-Turbo V8")
                .horsepower(710)
                .zeroToHundred(new BigDecimal("2.9"))
                .maxSpeed(340)
                .fuelType("가솔린")
                .driveType("RWD")
                .transmission("7단 DCT")
                .color("로소 코르사")
                .seats(2)
                .doors(2)
                .bodyType("쿠페")
                .description("페라리의 대표적인 미드엔진 V8 슈퍼카로, 순수한 드라이빙 퍼포먼스를 추구합니다.")
                .imageUrl("https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=600&h=400&fit=crop")
                .isLimitedEdition(false)
                .build(),

            CarEntity.builder()
                .brand(ferrari)
                .model("LaFerrari")
                .modelYear(2022)
                .price(new BigDecimal("1500000"))
                .priceCurrency("USD")
                .engine("6.3L V12 + 전기모터")
                .horsepower(950)
                .zeroToHundred(new BigDecimal("2.4"))
                .maxSpeed(370)
                .fuelType("하이브리드")
                .driveType("RWD")
                .transmission("7단 DCT")
                .color("옐로우 모데나")
                .seats(2)
                .doors(2)
                .bodyType("쿠페")
                .description("페라리의 플래그십 하이퍼카로, 최첨단 하이브리드 기술과 F1 기술이 집약되었습니다.")
                .imageUrl("https://images.unsplash.com/photo-1544829099-b9a0c5303bea?w=600&h=400&fit=crop")
                .isLimitedEdition(true)
                .productionCount(499)
                .build(),

            CarEntity.builder()
                .brand(ferrari)
                .model("296 GTB")
                .modelYear(2023)
                .price(new BigDecimal("320000"))
                .priceCurrency("USD")
                .engine("2.9L Twin-Turbo V6 + 전기모터")
                .horsepower(830)
                .zeroToHundred(new BigDecimal("2.9"))
                .maxSpeed(330)
                .fuelType("하이브리드")
                .driveType("RWD")
                .transmission("8단 DCT")
                .color("블루 코르사")
                .seats(2)
                .doors(2)
                .bodyType("쿠페")
                .description("페라리 최초의 V6 하이브리드 베를리네타로, 새로운 시대의 페라리를 대표합니다.")
                .imageUrl("https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=600&h=400&fit=crop")
                .isLimitedEdition(false)
                .build(),

            // BMW 자동차들
            CarEntity.builder()
                .brand(bmw)
                .model("M3 Competition")
                .modelYear(2023)
                .price(new BigDecimal("85000"))
                .priceCurrency("USD")
                .engine("3.0L Twin-Turbo I6")
                .horsepower(510)
                .zeroToHundred(new BigDecimal("3.8"))
                .maxSpeed(290)
                .fuelType("가솔린")
                .driveType("RWD")
                .transmission("8단 자동")
                .color("알파인 화이트")
                .seats(5)
                .doors(4)
                .bodyType("세단")
                .description("BMW M의 정수가 담긴 고성능 스포츠 세단으로, 트랙과 일상 모두에서 완벽한 성능을 제공합니다.")
                .imageUrl("https://images.unsplash.com/photo-1555215695-3004980ad54e?w=600&h=400&fit=crop")
                .isLimitedEdition(false)
                .build(),

            CarEntity.builder()
                .brand(bmw)
                .model("i8")
                .modelYear(2022)
                .price(new BigDecimal("150000"))
                .priceCurrency("USD")
                .engine("1.5L Turbo I3 + 전기모터")
                .horsepower(374)
                .zeroToHundred(new BigDecimal("4.4"))
                .maxSpeed(250)
                .fuelType("하이브리드")
                .driveType("AWD")
                .transmission("6단 자동")
                .color("크리스탈 화이트")
                .seats(2)
                .doors(2)
                .bodyType("쿠페")
                .description("BMW의 미래 지향적 하이브리드 스포츠카로, 혁신적인 디자인과 친환경 기술을 결합했습니다.")
                .imageUrl("https://images.unsplash.com/photo-1617788138017-80ad40651399?w=600&h=400&fit=crop")
                .isLimitedEdition(true)
                .productionCount(20448)
                .build(),

            // Porsche 자동차들
            CarEntity.builder()
                .brand(porsche)
                .model("911 Turbo S")
                .modelYear(2023)
                .price(new BigDecimal("230000"))
                .priceCurrency("USD")
                .engine("3.8L Twin-Turbo Flat-6")
                .horsepower(650)
                .zeroToHundred(new BigDecimal("2.6"))
                .maxSpeed(330)
                .fuelType("가솔린")
                .driveType("AWD")
                .transmission("8단 PDK")
                .color("가드 레드")
                .seats(4)
                .doors(2)
                .bodyType("쿠페")
                .description("포르쉐 911의 최고 성능 버전으로, 완벽한 균형과 절대적인 성능을 자랑합니다.")
                .imageUrl("https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=600&h=400&fit=crop")
                .isLimitedEdition(false)
                .build()
        );

        carRepository.saveAll(cars);
        System.out.println("초기 자동차 데이터 " + cars.size() + "개가 성공적으로 로드되었습니다.");
    }
}