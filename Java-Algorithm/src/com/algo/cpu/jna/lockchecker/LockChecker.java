/*
 * Copyright 2016-2022 chronicle.software
 *
 *       https://chronicle.software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.algo.cpu.jna.lockchecker;

import java.io.IOException;

/**
 * @author Tom Shercliff
 */

public interface LockChecker {

    boolean isLockFree(int id);

    boolean obtainLock(int id, String metaInfo) throws IOException;

    boolean releaseLock(int id);

    String getMetaInfo(int id) throws IOException;
}