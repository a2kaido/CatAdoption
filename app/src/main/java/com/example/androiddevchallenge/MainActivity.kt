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

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") { CatList(navController) }
        composable("detail") {
            CatDetail(
                navController.previousBackStackEntry!!.arguments!!.getParcelable(
                    "cat"
                )!!
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        AppNavigation()
    }
}

@Composable
fun CatList(navController: NavHostController) {
    LazyColumn {
        items(catList()) {
            Card(
                modifier = Modifier
                    .clickable {
                        navController.currentBackStackEntry?.arguments?.putParcelable("cat", it)
                        navController.navigate("detail")
                    }
                    .fillParentMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = it.res),
                    "cat"
                )
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h5,
                    color = it.nameColor,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CatDetail(cat: Cat) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = cat.res),
            "cat"
        )
        Spacer(modifier = Modifier.padding(8.dp))
        var visible by remember { mutableStateOf(false) }
        Handler(Looper.getMainLooper()).postDelayed({ visible = true }, 300)
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(),
        ) {
            Text(
                cat.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp),
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(),
        ) {
            Text(
                "\$${cat.price}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(700)
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Adopt",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
