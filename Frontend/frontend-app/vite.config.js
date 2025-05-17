import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vite.dev/config/
export default defineConfig({
  server: {
    proxy: {
      '/products': 'http://localhost:8080',
      '/payments': 'http://localhost:8080'
    }
  },
  plugins: [react()],
})
