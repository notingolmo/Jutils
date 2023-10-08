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

/**
 * Error result representation.The class encapsulates {@link Exception} type, which is thrown by
 * original method call. If the caller tries to get the value on error type a {@link Panic} will
 * be thrown.
 *
 * @param <T>
 */
public class Error<T> extends Result<T> {

  private final Exception exception;

  public Error(Exception exception) {
    super(null);
    this.exception = exception;
  }

  public T get() {
    throw new Panic(exception);
  }

  public boolean isError() {
    return true;
  }

  public Result<T> onErrorFail() {
    throw new Panic(exception);
  }
}
