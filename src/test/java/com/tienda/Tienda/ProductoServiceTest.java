package com.tienda.Tienda;


import com.tienda.Tienda.dto.ProductoDTO;
import com.tienda.Tienda.dto.ProductoRequestDTO;
import com.tienda.Tienda.exception.CategoriaNotFoundException;
import com.tienda.Tienda.model.Categoria;
import com.tienda.Tienda.model.Producto;
import com.tienda.Tienda.repository.CategoriaRepository;
import com.tienda.Tienda.repository.ProductoRepository;
import com.tienda.Tienda.service.CategoryServiceImpl;
import com.tienda.Tienda.service.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private Categoria categoria;

    @BeforeEach
    void setUp(){
        categoria = Categoria.builder()
                .id(1L)
                .nombre("Electrónica")
                .descripcion("Productos electrónicos")
                .build();
        producto = Producto.builder()
                .id(1L)
                .nombre("Portatil")
                .precio(999.99)
                .stock(10)
                .disponible(true)
                .categoria(categoria)
                .build();
    }
    @Test
    void crear_debeGuardar_conCategoriaCorrecta(){
        var request = new ProductoRequestDTO("Teclado",79.99,50,true,1L);
        var productoGuardado = Producto.builder()
                .id(2L)
                .nombre("Teclado")
                .precio(79.99)
                .stock(50)
                .disponible(true)
                .categoria(categoria)
                .build();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);
        ProductoDTO resultado = productoService.crear(request);

        assertThat(resultado.id()).isEqualTo(2L);
        assertThat(resultado.categoriaNombre()).isEqualTo(categoria.getNombre());
        verify(productoRepository,times(1)).save(any(Producto.class));

    }
    @Test
    void crear_cuandoCategoriaNoExiste_debeLanzarExcepcion(){
        var request = new ProductoRequestDTO("Teclado",79.99,50,true,99L);
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> productoService.crear(request))
                .isInstanceOf(CategoriaNotFoundException.class);
        verify(productoRepository,never()).save(any());
    }
    @Test
    void obtenerPorCategoria_debeDevolver_productosDeEsaCategoria(){
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.findByCategoria(categoria)).thenReturn(List.of(producto));

        List<ProductoDTO> resultado = productoService.obtenerPorCategoria(categoria.getId());

        assertThat(resultado).hasSize(1);
        assertThat(resultado.getFirst().nombre()).isEqualTo("Portatil");
    }
}
