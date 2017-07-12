Copyright 2017 Crown Copyright

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

# Koryphe

Koryphe is an extensible functions library for filtering, aggregating and transforming data based on the Java Function API. It provides:

 - Context adapted functions.
 - Function composition.
 - Functions with multiple inputs/outputs.
 - JSON serialisation.
 - Library of re-usable functions.
 
## Context adapted functions

Koryphe allows any Java 8 Function, BinaryOperator or Predicate to be adapted and applied to the values contained within a complex object such as a Tuple.

## Function composition

Functions (or BinaryOperators or Predicates) can be combined and applied together in a single composite function. This is particularly powerful when combined with context adapted functions, allowing composite functions to be built from a library of simple functions and applied to complex objects.

## Functions with multiple inputs/outputs

Koryphe can combine inputs and outputs into tuples, providing type-safe interfaces for functions that apply to and/or return more than one value.

## JSON serialisation

Functions, including composites, can be JSON serialised, allowing them to be used across distributed applications.

## Library of re-usable functions

Koryphe provides a number of useful Functions, BinaryOperators and Predicates for building simple applications.