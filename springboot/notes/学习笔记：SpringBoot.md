# 参考链接

* [Introduction · Spring Boot参考指南](https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/)
* [springboot(一)：入门篇 - 纯洁的微笑博客](http://www.ityouknow.com/springboot/2016/01/06/spring-boot-quick-start.html)
* [Spring Boot 教程、技术栈示例代码，快速简单上手教程。](https://github.com/ityouknow/spring-boot-examples)

# 要点

## 测试

### Spring Mvc 单元测试示例

* 使用MockMvc

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcExampleTests {
	@Autowired
	private MockMvc mvc;

	@Test
	public void exampleTest() throws Exception {
		this.mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello World"));
	}
}
```

* 使用WebTestClient

```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortWebTestClientExampleTests {
	@Autowired
	private WebTestClient webClient;

	@Test
	public void exampleTest() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World");
	}
}
```

* 使用TestRestTemplate

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortTestRestTemplateExampleTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Hello World");
	}
}
```

## 需要注意的注解

* `@MockBean`
  * 用来在测试内中模拟外部依赖，会导致测试变慢。对于外部依赖，最好在配置层完成模拟，并配置不同的Profile。
  * 当需要注入用户自定义的bean时可以使用`@MockBean`。
* `@MockBean`和`@SpyBean`
  * `@MockBean`是全新的对象，与正式对象没有关系，而`@SpyBean`与正式对象紧密联系，可以模拟部分方法。
* `@TestWIthMockUser`
  * 通过模拟角色进行mvc测试。此后可以使用MockMvc等进行单元测试。

## Specification接口的使用以及Criteria查询的一般用法

