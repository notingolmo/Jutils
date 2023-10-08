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

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Result object of an execution, which wraps the value returned from it unless it fails. Otherwise,
 * the result will be an instance of {@link Error}, if the execution fails, or {@link None} if it
 * doesn't return anything.
 *
 * @param <T>
 */
public class Result<T> {

  private final T result;

  public Result(T result) {
    this.result = result;
  }

  public T get() {
    return result;
  }

  public boolean isNone() {
    return false;
  }

  public boolean isError() {
    return false;
  }

  public Result<T> onErrorThen(Supplier<T> supplier) {
    return isError() ? new Result<>(supplier.get()) : this;
  }

  public T onErrorThen(T value) {
    return isError() ? value : result;
  }

  public Result<T> onErrorFail() {
    return this;
  }

  public Result<T> onNoneThen(Supplier<T> supplier) {
    return isNone() ? new Result<>(supplier.get()) : this;
  }

  public T onNoneThen(T value) {
    return isNone() ? value : result;
  }

  public T onSuccessGet() {
    return !isError() ? result : null;
  }

  public static <T> None<T> none() {
    return new None<>();
  }

  public static <T> Result<T> of(T value) {
    return new Result<>(Objects.requireNonNull(value));
  }

  public static <T> Result<T> from(Callable<T> unsafeCallable) {
    try {
      return new Result<>(unsafeCallable.call());
    } catch (Exception e) {
      return new Error<>(e);
    }
  }
}
