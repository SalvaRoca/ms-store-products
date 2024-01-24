CREATE SCHEMA IF NOT EXISTS products;

USE products;

CREATE TABLE IF NOT EXISTS products (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_ref VARCHAR(255) UNIQUE,
    category    VARCHAR(255),
    brand       VARCHAR(255),
    model       VARCHAR(255),
    description VARCHAR(2550),
    price       DOUBLE
);

INSERT INTO products (product_ref, category, brand, model, description, price)
VALUES
    ('B01', 'basses', 'Fender', 'Precision Bass', 'El Fender Precision Bass, también conocido como P-Bass, revolucionó la música al ser el primer bajo eléctrico con trastes y un diseño que sentó las bases para muchos modelos posteriores. Su sonido firme y su versatilidad lo han convertido en un estándar en la industria musical.', 1500.0),

    ('B02', 'basses', 'Fender', 'Precision Bass V', 'Una variante del clásico Precision Bass con 5 cuerdas, ofrece un rango extendido para aquellos que buscan tonos más profundos y versátiles.', 1700.0),

    ('B03', 'basses', 'Fender', 'Jazz Bass', 'El Fender Jazz Bass, conocido como J-Bass, se caracteriza por su sonido brillante y versátil, ideal para una amplia gama de géneros musicales. Su diseño y sus pastillas permiten una mayor flexibilidad tonal.', 1700.0),

    ('B04', 'basses', 'Fender', 'Jazz Bass Deluxe V', 'Una variante de 5 cuerdas del clásico Jazz Bass, ofrece un mayor rango tonal y versatilidad para los músicos que buscan más opciones.', 1900.0),

    ('B05', 'basses', 'Music Man', 'StingRay', 'El Music Man StingRay es reconocido por su sonido potente y su construcción robusta. Sus pastillas de alta salida brindan un tono distintivo y su diseño ergonómico lo hace cómodo de tocar durante largas sesiones.', 2000.0),

    ('A01', 'amps', 'Ampeg', 'SVT-7PRO', 'El Ampeg SVT-7PRO es un amplificador de bajo de alta potencia conocido por su sonido cálido y vintage. Con 1000 vatios de potencia, ofrece una amplia gama tonal y es popular entre bajistas de diversos géneros.', 1100.0),

    ('A02', 'amps', 'Ampeg', 'SVT-CL', 'El Ampeg SVT-CL es un amplificador de bajo icónico conocido por su sonido vintage y su potencia impactante de 300 vatios. Es una recreación del legendario SVT original, ofreciendo un tono cálido y clásico.', 2000.0),

    ('A03', 'amps', 'Gallien-Krueger', 'MB800', 'El Gallien-Krueger MB800 es un amplificador ligero y compacto con 800 vatios de potencia. Con una variedad de controles de ecualización, ofrece un sonido versátil y es ideal para músicos que buscan portabilidad sin comprometer el tono.', 900.0),

    ('A04', 'amps', 'Gallien-Krueger', 'MB Fusion 800', 'El Gallien-Krueger MB Fusion 800 es un amplificador híbrido que combina una etapa de preamplificación a válvulas con una etapa de potencia de estado sólido, ofreciendo 800 vatios de potencia. Ofrece una amplia gama de tonos y versatilidad.', 1400.0),

    ('A05', 'amps', 'Markbass', 'Little Mark III', 'El Markbass Little Mark III es conocido por su diseño pequeño pero potente, con 500 vatios de potencia. Ofrece un sonido limpio y claro que se adapta bien a diferentes estilos musicales, y es apreciado por su portabilidad.', 700.0),

    ('E01', 'fx', 'Electro Harmonix', 'Big Muff π', 'El Electro Harmonix Big Muff π es un pedal legendario de fuzz conocido por su sonido distorsionado y saturado. Ha sido utilizado por muchos bajistas icónicos y ofrece una distorsión gruesa y potente.', 120.0),

    ('E02', 'fx', 'Darkglass Electronics', 'Microtubes B7K Ultra', 'El Darkglass Microtubes B7K Ultra es un pedal versátil que combina preamplificación y distorsión. Ofrece controles detallados de tono y saturación, proporcionando un sonido rico y potente.', 400.0),

    ('E03', 'fx', 'Darkglass Electronics', 'Alpha·Omega Ultra', 'El Darkglass Alpha·Omega Ultra es un pedal de preamplificación y distorsión que ofrece una amplia gama de tonos desde cálidos hasta agresivos. Es apreciado por su versatilidad tonal y su potencia.', 450.0),

    ('E04', 'fx', 'EBS', 'MultiComp', 'El EBS MultiComp es un compresor conocido por su capacidad para suavizar y controlar dinámicas. Ofrece opciones de compresión versátiles y es apreciado por su transparencia y calidad de sonido.', 250.0),

    ('E05', 'fx', 'MXR', 'M82 Bass Envelope Filter', 'El MXR M82 Bass Envelope Filter es un pedal de filtro que responde a las dinámicas del bajo, proporcionando efectos funky y envolventes. Ofrece controles detallados para esculpir el tono deseado.', 180.0);
