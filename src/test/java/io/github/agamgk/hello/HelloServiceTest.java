package io.github.agamgk.hello;


import io.github.agamgk.lang.Lang;
import io.github.agamgk.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class HelloServiceTest {
    public static final String  WELCOME = "Hello";
    public static final String  FALLBACK_IDWELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, -1);
        //then
        assertEquals(WELCOME +" " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        //when
        var result = SUT.prepareGreeting(name, -1);
        //then
        assertEquals(WELCOME+ " " + name, result);
    }

    @Test
    public void test_prepareGreeting_nullLanguage_returnsGreetingWithFallbackIdLang() {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_IDWELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    /*@Test
    public void test_prepareGreeting_textLanguage_returnsGreetingWithFallbackIdLang() {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, "abc");
        //then
        assertEquals(FALLBACK_IDWELCOME + " " + HelloService.FALLBACK_NAME, result);
    }*/
    @Test
    public void test_prepareGreeting_nonExistingLanguage_returnsGreetingWithFallbackLang() {
        //given
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, -1);
        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME, result);
    }

    //Tworzy i zwraca repozytorium wykorzystywane w testach
    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
    //Tworzy i zwraca repozytorium wykorzystywane w testach
    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_IDWELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
}
