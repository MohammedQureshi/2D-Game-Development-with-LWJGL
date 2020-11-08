package com.apollo.timewreak.inputOutput;

import org.lwjgl.PointerBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetMonitors;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;

public class Screen {
    /**
     * Returns the primary monitor. This is usually the monitor where elements like the task bar or global menu bar are located.
     * <p></p>
     * <p>This function must only be called from the main thread.</p>
     * <p></p>
     * <p>The primary monitor is always first in the array returned by {@link org.lwjgl.glfw.GLFW#glfwGetMonitors GetMonitors}.</p>
     * @return the primary monitor, or {@code NULL} if no monitors were found or if an error occurred
     */
    public static long getPrimaryDisplay() { return glfwGetPrimaryMonitor(); }
    /**
     * Returns an array of handles for all currently connected monitors. The primary monitor is always first in the returned array. If no monitors were found,
     * this function returns {@code NULL}.
     * <p></p>
     * <p>This function must only be called from the main thread.</p>
     * <p></p>
     * <p>For the original documentation see {@link org.lwjgl.glfw.GLFW#glfwGetMonitors() }</p>
     * @return an array of monitor handlers, or {@code NULL} if no monitors were found or if an error occurred
     */
    public static PointerBuffer getDisplays() { return glfwGetMonitors(); }
}