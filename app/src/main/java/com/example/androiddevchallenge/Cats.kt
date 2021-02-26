/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val res: Int,
    val nameColor: Color,
    val name: String,
    val price: Int
) : Parcelable

fun catList() = listOf(
    Cat(R.drawable.cat1, Color.Black, "I", 100),
    Cat(R.drawable.cat2, Color.Black,"Want", 500),
    Cat(R.drawable.cat3, Color.Black, "To", 800),
    Cat(R.drawable.cat4, Color.White, "Have", 900),
    Cat(R.drawable.cat5, Color.White, "Shared", 800),
    Cat(R.drawable.cat6, Color.White, "Elements", 500),
    Cat(R.drawable.cat1, Color.Black, "I", 100),
    Cat(R.drawable.cat2, Color.Black,"Want", 500),
    Cat(R.drawable.cat3, Color.Black, "To", 800),
    Cat(R.drawable.cat4, Color.White, "Have", 900),
    Cat(R.drawable.cat5, Color.White, "Shared", 800),
    Cat(R.drawable.cat6, Color.White, "Elements", 500),
    Cat(R.drawable.cat1, Color.Black, "I", 100),
    Cat(R.drawable.cat2, Color.Black,"Want", 500),
    Cat(R.drawable.cat3, Color.Black, "To", 800),
    Cat(R.drawable.cat4, Color.White, "Have", 900),
    Cat(R.drawable.cat5, Color.White, "Shared", 800),
    Cat(R.drawable.cat6, Color.White, "Elements", 500),
)
