package org.romanov.yurt.post.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.model.PostModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class ReversePostConverterUnitTests {

    @InjectMocks
    private ReversePostConverter instance;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldConvert() {
        // given
        var postData = mock(PostData.class);

        // when
        var result = instance.convert(postData);

        // then
        verify(modelMapper).map(postData, PostModel.class);
    }
}