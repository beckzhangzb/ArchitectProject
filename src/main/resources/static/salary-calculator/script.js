// Real-time Salary Calculator with Golden Animations

// DOM Elements
const monthlySalaryInput = document.getElementById('monthlySalary');
const workHoursInput = document.getElementById('workHours');
const workDaysInput = document.getElementById('workDays');
const startBtn = document.getElementById('startBtn');
const resetBtn = document.getElementById('resetBtn');
const moneyValueEl = document.getElementById('moneyValue');
const perSecondEl = document.getElementById('perSecond');
const totalSecondsEl = document.getElementById('totalSeconds');
const coinCountEl = document.getElementById('coinCount');
const goldEarnedEl = document.getElementById('goldEarned');
const coinContainer = document.getElementById('coinContainer');

// State variables
let timerId = null;
let startTime = 0;
let accumulatedMoney = 0;
let totalSeconds = 0;
let coinCount = 0;
let isRunning = false;

// Calculate money per second based on inputs
function calculateMoneyPerSecond() {
    const monthlySalary = parseFloat(monthlySalaryInput.value) || 15000;
    const workHours = parseFloat(workHoursInput.value) || 8;
    const workDays = parseFloat(workDaysInput.value) || 22;
    
    // Calculate total working hours per month
    const totalWorkHours = workHours * workDays;
    
    // Calculate hourly rate
    const hourlyRate = monthlySalary / totalWorkHours;
    
    // Calculate money per second (3600 seconds in an hour)
    const moneyPerSecond = hourlyRate / 3600;
    
    return moneyPerSecond;
}

// Format currency with ¥ symbol and 2 decimal places
function formatCurrency(amount) {
    return '¥' + amount.toFixed(2);
}

// Create a golden coin animation
function createGoldenCoin() {
    const coin = document.createElement('div');
    coin.className = 'coin';
    
    // Random position
    const leftPos = Math.random() * 100;
    coin.style.left = `${leftPos}%`;
    
    // Random size between 40-80px
    const size = 40 + Math.random() * 40;
    coin.style.width = `${size}px`;
    coin.style.height = `${size}px`;
    
    // Random animation duration between 3-6 seconds
    const duration = 3 + Math.random() * 3;
    coin.style.animationDuration = `${duration}s`;
    
    // Add to container
    coinContainer.appendChild(coin);
    
    // Remove from DOM after animation completes
    setTimeout(() => {
        if (coin.parentNode === coinContainer) {
            coinContainer.removeChild(coin);
        }
    }, duration * 1000);
}

// Show coin drop effect when reaching multiples of 10
function checkAndDropCoins(currentMoney) {
    // Check if current money is a multiple of 10
    const roundedMoney = Math.floor(currentMoney);
    if (roundedMoney > 0 && roundedMoney % 10 === 0) {
        // Create 3-5 coins for each multiple of 10
        const coinsToDrop = 3 + Math.floor(Math.random() * 3);
        for (let i = 0; i < coinsToDrop; i++) {
            setTimeout(() => {
                createGoldenCoin();
            }, i * 200); // Stagger the coin drops
        }
        
        coinCount += coinsToDrop;
        coinCountEl.textContent = coinCount;
        
        // Add some visual feedback
        moneyValueEl.style.transform = 'scale(1.1)';
        setTimeout(() => {
            moneyValueEl.style.transform = 'scale(1)';
        }, 300);
    }
}

// Update display values
function updateDisplay() {
    const moneyPerSecond = calculateMoneyPerSecond();
    
    // Calculate accumulated money
    const elapsedSeconds = Date.now() - startTime;
    accumulatedMoney = (elapsedSeconds / 1000) * moneyPerSecond;
    
    // Update UI
    moneyValueEl.textContent = formatCurrency(accumulatedMoney);
    perSecondEl.textContent = `+${formatCurrency(moneyPerSecond)}/秒`;
    totalSecondsEl.textContent = Math.floor(elapsedSeconds / 1000);
    
    // Check for coin drops
    checkAndDropCoins(accumulatedMoney);
    
    // Update golden wealth stat (showing how many times we've hit multiples of 10)
    const goldLevel = Math.floor(accumulatedMoney / 10);
    goldEarnedEl.textContent = goldLevel;
}

// Start the calculation
function startCalculation() {
    if (isRunning) return;
    
    // Reset if needed
    if (timerId) {
        clearInterval(timerId);
    }
    
    // Initialize state
    startTime = Date.now();
    accumulatedMoney = 0;
    totalSeconds = 0;
    coinCount = 0;
    
    // Update UI
    moneyValueEl.textContent = formatCurrency(0);
    perSecondEl.textContent = `+${formatCurrency(calculateMoneyPerSecond())}/秒`;
    totalSecondsEl.textContent = '0';
    coinCountEl.textContent = '0';
    goldEarnedEl.textContent = '0';
    
    // Start the timer
    timerId = setInterval(updateDisplay, 50); // Update every 50ms for smooth animation
    isRunning = true;
    
    // Add visual feedback to button
    startBtn.innerHTML = '运行中... <span class="loading"></span>';
    startBtn.disabled = true;
}

// Reset the calculator
function resetCalculator() {
    if (timerId) {
        clearInterval(timerId);
        timerId = null;
    }
    
    isRunning = false;
    accumulatedMoney = 0;
    totalSeconds = 0;
    coinCount = 0;
    
    // Reset UI
    moneyValueEl.textContent = formatCurrency(0);
    perSecondEl.textContent = `+${formatCurrency(calculateMoneyPerSecond())}/秒`;
    totalSecondsEl.textContent = '0';
    coinCountEl.textContent = '0';
    goldEarnedEl.textContent = '0';
    
    // Clear coin container
    coinContainer.innerHTML = '';
    
    // Reset button
    startBtn.innerHTML = '开始计算';
    startBtn.disabled = false;
}

// Event listeners
startBtn.addEventListener('click', startCalculation);
resetBtn.addEventListener('click', resetCalculator);

// Initialize with default values
updateDisplay();

// Add keyboard support (Enter to start, Escape to reset)
document.addEventListener('keydown', (e) => {
    if (e.key === 'Enter' && !isRunning) {
        startCalculation();
    } else if (e.key === 'Escape') {
        resetCalculator();
    }
});

// Add some initial golden particles for visual appeal
function addInitialParticles() {
    for (let i = 0; i < 20; i++) {
        setTimeout(() => {
            const coin = document.createElement('div');
            coin.className = 'coin';
            coin.style.left = `${Math.random() * 100}%`;
            coin.style.width = `${30 + Math.random() * 30}px`;
            coin.style.height = `${30 + Math.random() * 30}px`;
            coin.style.animationDuration = `${2 + Math.random() * 2}s`;
            coin.style.opacity = '0.3';
            coinContainer.appendChild(coin);
            
            setTimeout(() => {
                if (coin.parentNode === coinContainer) {
                    coinContainer.removeChild(coin);
                }
            }, 2000);
        }, i * 100);
    }
}

// Add initial particles when page loads
window.addEventListener('load', () => {
    addInitialParticles();
});

// Make sure we clean up on page unload
window.addEventListener('beforeunload', () => {
    if (timerId) {
        clearInterval(timerId);
    }
});