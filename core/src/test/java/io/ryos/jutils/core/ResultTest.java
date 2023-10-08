/*******************************************************************************
 * Copyright (c) 2023 Erhan Bagdemir. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package io.ryos.jutils.core;

import static org.assertj.core.api.Assertions.assertThat;

import io.ryos.jutils.string.Str;
import java.util.concurrent.Callable;
import org.junit.jupiter.api.Test;

public class ResultTest {

  private static final String TEST_STR = "test";

  @Test
  void ensureThatResultReturnValue() {
    assertThat(Str.of(Result.from(() -> TEST_STR).get()).isEqualTo(TEST_STR)).isTrue();
  }

  @Test
  void ensureThatIsError() {
    assertThat(Result.from(fail()).isError()).isTrue();
  }

  private Callable<Object> fail() {
    return () -> {
      throw new RuntimeException("Shouldn't fail");
    };
  }
}
