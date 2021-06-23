/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.chrisbanes.insetter.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import dev.chrisbanes.insetter.applyInsetter
import dev.chrisbanes.insetter.sample.databinding.ActivityBreakageSampleBinding

class BreakageSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBreakageSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.top.applyInsetter {
            type(statusBars = true/*, displayCutout = true*/) {
                margin(top = true, animated = true)
            }
            type(navigationBars = true) {
                margin(horizontal = true)
            }
        }
        binding.bottom.applyInsetter {
            type(navigationBars = true/*, displayCutout = true*/) {
                margin(bottom = true, horizontal = true, animated = true)
            }
        }

        val wicc = WindowInsetsControllerCompat(window, binding.root)
        wicc.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        var statusHidden = false
        binding.root.setOnClickListener {
            if (statusHidden) {
                wicc.show(WindowInsetsCompat.Type.systemBars())
            } else {
                wicc.hide(WindowInsetsCompat.Type.systemBars())
            }
            statusHidden = !statusHidden
        }
    }
}
