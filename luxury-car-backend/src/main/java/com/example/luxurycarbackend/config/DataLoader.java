package com.example.luxurycarbackend.config;

import com.example.luxurycarbackend.brand.entity.BrandEntity;
import com.example.luxurycarbackend.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BrandRepository brandRepository;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0) {
            loadInitialBrands();
        }
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
                .representative("F8 Tributo, LaFerrari")
                .image("/Logos/Ferrari.svg")
                .build(),
            BrandEntity.builder()
                .name("Lamborghini")
                .nameKr("람보르기니")
                .founded(1963)
                .country("이탈리아")
                .category("스포츠카")
                .description("황소 로고로 유명한 이탈리아 슈퍼카 브랜드, 공격적인 디자인과 강력한 성능으로 유명합니다.")
                .representative("Huracán, Aventador")
                .image("/Logos/Lamborghini.svg")
                .build(),
            BrandEntity.builder()
                .name("Rolls-Royce")
                .nameKr("롤스로이스")
                .founded(1904)
                .country("영국")
                .category("럭셔리")
                .description("영국 왕실이 사랑하는 최고급 럭셔리카 브랜드, 수작업으로 제작되는 극상의 품질을 자랑합니다.")
                .representative("Phantom, Ghost")
                .image("/Logos/ROLLS-ROYCE.svg")
                .build(),
            BrandEntity.builder()
                .name("Bentley")
                .nameKr("벤틀리")
                .founded(1919)
                .country("영국")
                .category("럭셔리")
                .description("영국의 프리미엄 럭셔리 브랜드로, 전통적인 장인정신과 현대적 기술의 완벽한 조화를 보여줍니다.")
                .representative("Continental GT, Bentayga")
                .image("/Logos/Bentley.svg")
                .build(),
            BrandEntity.builder()
                .name("McLaren")
                .nameKr("맥라렌")
                .founded(1963)
                .country("영국")
                .category("스포츠카")
                .description("F1에서 검증된 기술력으로 제작되는 영국의 슈퍼카 브랜드, 혁신적인 엔지니어링이 특징입니다.")
                .representative("720S, P1")
                .image("/Logos/McLaren.svg")
                .build(),
            BrandEntity.builder()
                .name("Porsche")
                .nameKr("포르쉐")
                .founded(1931)
                .country("독일")
                .category("스포츠카")
                .description("독일의 대표적인 스포츠카 브랜드로, 911 시리즈로 유명하며 정밀한 독일 엔지니어링의 대명사입니다.")
                .representative("911, Cayenne")
                .image("/Logos/Porsche.svg")
                .build(),
            BrandEntity.builder()
                .name("Aston Martin")
                .nameKr("애스턴마틴")
                .founded(1913)
                .country("영국")
                .category("럭셔리")
                .description("1913년 설립된 영국의 프리미엄 스포츠카 브랜드로, 제임스 본드 시리즈와 F1 레이싱으로 유명한 럭셔리 자동차 제조사입니다.")
                .representative("DB11, DBX, 밴티지")
                .image("/Logos/Aston Martin.svg")
                .build(),
            BrandEntity.builder()
                .name("Mercedes-Benz")
                .nameKr("메르세데스-벤츠")
                .founded(1926)
                .country("독일")
                .category("럭셔리")
                .description("독일의 대표적인 프리미엄 자동차 브랜드로, AMG 라인으로 스포츠카 영역까지 아우르는 종합 럭셔리 브랜드입니다.")
                .representative("S-Class, AMG GT, G-Wagon")
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
}