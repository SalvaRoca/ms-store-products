package com.bassmania.msstoreproducts.controller;

import com.bassmania.msstoreproducts.model.Product;
import com.bassmania.msstoreproducts.model.ProductDto;
import com.bassmania.msstoreproducts.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Controlador para la gestión de pedidos")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(
            operationId = "Consultar pedidos",
            description = "Operación de lectura",
            summary = "La API devuelve la lista completa de productos que coincidan con los criterios de búsqueda indicados en los query params",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de productos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No se han encontrado productos que coincidan con los criterios de búsqueda indicados"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productRef,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price
    ) {
        List<Product> productList = productService.getProducts(productRef, category, brand, model, description, price);

        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{productRef}")
    @Operation(
            operationId = "Consultar un producto por referencia",
            description = "Operación de lectura",
            summary = "La API devuelve un producto registrado en la base de datos según su referencia",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado ningún producto con la referencia indicada"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<Product> getProductByProductRef(@PathVariable String productRef) {
        Product product = productService.getProductByProductRef(productRef);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            operationId = "Registrar un producto",
            description = "Operación de escritura",
            summary = "La API registra un nuevo producto en la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto registrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Alguno de los datos de entrada es incorrecto, o se ha indicado una referencia de un producot ya registrado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        try {
            Product savedProduct = productService.createProduct(productDto);

            if (savedProduct != null) {
                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some product details are missing, please check and try again\"}");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productDto.getProductRef() + " already existing, please try with another one\"}");
        }
    }

    @PutMapping("/{productRef}")
    @Operation(
            operationId = "Modificar un producto",
            description = "Operación de escritura",
            summary = "La API modifica un producto registrado en la base de datos según los datos indicados en el cuerpo de la petición",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto modificado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Alguno de los datos de entrada es incorrecto, o se ha indicado una referencia de un producot ya registrado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado ningún producto con la referencia indicada"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<?> updateProduct(@PathVariable String productRef, @RequestBody ProductDto productDto) {
        Product existingProduct = productService.getProductByProductRef(productRef);

        try {
            if (existingProduct != null) {
                Product updatedProduct = productService.updateProduct(existingProduct, productDto);

                if (updatedProduct != null) {
                    return ResponseEntity.ok(updatedProduct);
                } else {
                    return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some product details are missing, please check and try again\"}");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productDto.getProductRef() + " already existing, please try with another one\"}");
        }
    }

    @PatchMapping("/{productRef}")
    @Operation(
            operationId = "Modificar un producto",
            description = "Operación de escritura",
            summary = "La API modifica un producto registrado en la base de datos según los datos indicados en el cuerpo de la petición",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto modificado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Alguno de los datos de entrada es incorrecto, o se ha indicado una referencia de un producto ya registrado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado ningún producto con la referencia indicada"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<?> patchProduct(@PathVariable String productRef, @RequestBody String patchBody) {
        Product existingProduct = productService.getProductByProductRef(productRef);
        if (existingProduct != null) {
            try {
                Product updatedProduct = productService.updateProduct(existingProduct, patchBody);

                if (updatedProduct != null) {
                    return ResponseEntity.ok(updatedProduct);
                } else {
                    return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some input data might be incorrect, please check and try again\"}");
                }
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productRef + " already existing, please try with another one\"}");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productRef}")
    @Operation(
            operationId = "Eliminar un producto",
            description = "Operación de escritura",
            summary = "La API elimina un producto registrado en la base de datos según su referencia",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Producto eliminado"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se ha encontrado ningún producto con la referencia indicada"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor"
                    )
            }
    )
    public ResponseEntity<?> deleteProduct(@PathVariable String productRef) {
        Product product = productService.getProductByProductRef(productRef);

        if (product != null) {
            productService.deleteProduct(productRef);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
